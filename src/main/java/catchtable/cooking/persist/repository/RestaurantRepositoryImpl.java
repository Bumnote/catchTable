package catchtable.cooking.persist.repository;

import catchtable.cooking.dto.*;
import catchtable.cooking.persist.domain.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RestaurantItemResponse> getRestaurants(String keyword) {

        QRestaurant qRestaurant = QRestaurant.restaurant;
        QMenu qMenu = QMenu.menu;
        QReview qReview = QReview.review;
        QWaiting qWaiting = QWaiting.waiting;
        QReservation qReservation = QReservation.reservation;

        List<Tuple> results = jpaQueryFactory.select(qRestaurant, qMenu, qReview, qWaiting, qReservation)
                .from(qRestaurant)
                .leftJoin(qMenu).on(qMenu.restaurant.eq(qRestaurant))
                .leftJoin(qReview).on(qReview.restaurant.eq(qRestaurant))
                .leftJoin(qWaiting).on(qWaiting.restaurant.eq(qRestaurant))
                .leftJoin(qReservation).on(qReservation.restaurant.eq(qRestaurant))
                .where(eqKeyword(keyword))
                .fetch();

        Map<Long, RestaurantItemResponse> restaurantMap = new LinkedHashMap<>();

        for (Tuple tuple : results) {
            Restaurant restaurant = tuple.get(qRestaurant);
            Menu menu = tuple.get(qMenu);
            Review review = tuple.get(qReview);
            Waiting waiting = tuple.get(qWaiting);
            Reservation reservation = tuple.get(qReservation);

            if (restaurant != null) {
                RestaurantItemResponse response = restaurantMap.computeIfAbsent(restaurant.getId(), id ->
                        new RestaurantItemResponse(
                                restaurant.getName(),
                                restaurant.getAddress(),
                                restaurant.getPhoneNumber(),
                                new ArrayList<>(),
                                new ArrayList<>(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        )
                );

                if (menu != null) response.getMenus().add(new MenuItemResponse(menu));
                if (review != null) response.getReviews().add(new ReviewItemResponse(review));
                if (waiting != null) response.getWaitings().add(new WaitingItemResponse(waiting));
                if (reservation != null) response.getReservations().add(new ReservationItemResponse(reservation));

            }
        }

        return new ArrayList<>(restaurantMap.values());

    }

    private BooleanExpression eqKeyword(String keyword) {
        if (StringUtils.isBlank(keyword)) return null;
        return QRestaurant.restaurant.name.eq(keyword);
    }
}
