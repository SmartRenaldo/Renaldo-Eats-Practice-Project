package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDish is a Querydsl query type for Dish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDish extends EntityPathBase<Dish> {

    private static final long serialVersionUID = -224855526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDish dish = new QDish("dish");

    public final QCategory category;

    public final StringPath code = createString("code");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QDish(String variable) {
        this(Dish.class, forVariable(variable), INITS);
    }

    public QDish(Path<? extends Dish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDish(PathMetadata metadata, PathInits inits) {
        this(Dish.class, metadata, inits);
    }

    public QDish(Class<? extends Dish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

