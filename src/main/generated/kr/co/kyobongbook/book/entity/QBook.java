package kr.co.kyobongbook.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -100725720L;

    public static final QBook book = new QBook("book");

    public final kr.co.kyobongbook.common.entity.QBaseEntity _super = new kr.co.kyobongbook.common.entity.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final ListPath<BookCategory, QBookCategory> bookCategories = this.<BookCategory, QBookCategory>createList("bookCategories", BookCategory.class, QBookCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isAvailable = createBoolean("isAvailable");

    public final StringPath notAvailableReason = createString("notAvailableReason");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

