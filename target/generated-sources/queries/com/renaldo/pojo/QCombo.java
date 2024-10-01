package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCombo is a Querydsl query type for Combo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCombo extends EntityPathBase<Combo> {

    private static final long serialVersionUID = 1618662670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCombo combo = new QCombo("combo");

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

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QCombo(String variable) {
        this(Combo.class, forVariable(variable), INITS);
    }

    public QCombo(Path<? extends Combo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCombo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCombo(PathMetadata metadata, PathInits inits) {
        this(Combo.class, metadata, inits);
    }

    public QCombo(Class<? extends Combo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

