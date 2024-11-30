package catchtable.cooking.persist.repository;

import catchtable.cooking.dto.RestaurantItemResponse;
import catchtable.cooking.persist.domain.QRestaurant;
import catchtable.cooking.persist.domain.Restaurant;
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
    public List<RestaurantItemResponse> getRestaurants(String keyword) {

        QRestaurant qRestaurant = QRestaurant.restaurant;

        List<Restaurant> restaurants = jpaQueryFactory.selectFrom(qRestaurant)
                .where(eqKeyword(keyword))
                .fetch();

        return restaurants.stream()
                .map(restaurant -> {
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
