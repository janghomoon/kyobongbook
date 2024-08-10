package kr.co.kyobongbook.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookCategory is a Querydsl query type for BookCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookCategory extends EntityPathBase<BookCategory> {

    private static final long serialVersionUID = -238975674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookCategory bookCategory = new QBookCategory("bookCategory");

    public final kr.co.kyobongbook.common.entity.QBaseEntity _super = new kr.co.kyobongbook.common.entity.QBaseEntity(this);

    public final QBook book;

    public final NumberPath<Long> bookCategoryId = createNumber("bookCategoryId", Long.class);

    public final EnumPath<kr.co.kyobongbook.book.infra.enums.CategoryEnums> category = createEnum("category", kr.co.kyobongbook.book.infra.enums.CategoryEnums.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBookCategory(String variable) {
        this(BookCategory.class, forVariable(variable), INITS);
    }

    public QBookCategory(Path<? extends BookCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookCategory(PathMetadata metadata, PathInits inits) {
        this(BookCategory.class, metadata, inits);
    }

    public QBookCategory(Class<? extends BookCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

