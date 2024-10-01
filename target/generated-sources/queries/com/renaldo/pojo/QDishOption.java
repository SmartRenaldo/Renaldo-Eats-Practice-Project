package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDishOption is a Querydsl query type for DishOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDishOption extends EntityPathBase<DishOption> {

    private static final long serialVersionUID = 1661399855L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDishOption dishOption = new QDishOption("dishOption");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final QDish dish;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final StringPath name = createString("name");

    public final StringPath value = createString("value");

    public QDishOption(String variable) {
        this(DishOption.class, forVariable(variable), INITS);
    }

    public QDishOption(Path<? extends DishOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDishOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDishOption(PathMetadata metadata, PathInits inits) {
        this(DishOption.class, metadata, inits);
    }

    public QDishOption(Class<? extends DishOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new QDish(forProperty("dish"), inits.get("dish")) : null;
    }

}

