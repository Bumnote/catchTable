package catchtable.cooking.persist.repository;

import catchtable.cooking.persist.domain.QRestaurant;
import catchtable.cooking.persist.domain.Restaurant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> getRestaurants(String keyword) {

        QRestaurant restaurant = QRestaurant.restaurant;

        return jpaQueryFactory.select(restaurant)
                .from(restaurant)
                .where(eqKeyword(keyword))
                .fetch();
    }

    private BooleanExpression eqKeyword(String keyword) {
    if (StringUtils.isBlank(keyword)) return null;
        return QRestaurant.restaurant.name.eq(keyword);
    }
}
