package catchtable.cooking.persist.repository;

import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.dto.RestaurantQueryResponse;
import catchtable.cooking.persist.domain.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RestaurantQueryResponse> getAllRestaurants(String keyword) {

        QRestaurant qRestaurant = QRestaurant.restaurant;
        QMenu qMenu = QMenu.menu;
        QReview qReview = QReview.review;
        QWaiting qWaiting = QWaiting.waiting;
        QReservation qReservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(RestaurantQueryResponse.class,
                        qRestaurant,
                        qMenu,
                        qReview,
                        qWaiting,
                        qReservation))
                .from(qRestaurant)
                .leftJoin(qMenu).on(qMenu.restaurant.eq(qRestaurant))
                .leftJoin(qReview).on(qReview.restaurant.eq(qRestaurant))
                .leftJoin(qWaiting).on(qWaiting.restaurant.eq(qRestaurant))
                .leftJoin(qReservation).on(qReservation.restaurant.eq(qRestaurant))
                .where(eqKeyword(keyword))
                .fetch();
    }

    @Override
    public List<RestaurantItemResponse> getRestaurants(String keyword) {
        QRestaurant qRestaurant = QRestaurant.restaurant;

        List<Restaurant> restaurants = jpaQueryFactory.selectFrom(qRestaurant)
                .where(eqKeyword(keyword))
                .fetch();

        return restaurants.stream().map(restaurant -> {
            return RestaurantItemResponse.builder()
                    .id(restaurant.getId())
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .phoneNumber(restaurant.getPhoneNumber())
                    .build();
        }).toList();
    }


    private BooleanExpression eqKeyword(String keyword) {
        if (StringUtils.isBlank(keyword)) return null;
        return QRestaurant.restaurant.name.eq(keyword);
    }
}
