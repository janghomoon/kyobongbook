package kr.co.kyobongbook.book.entity.id;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookCategoryId is a Querydsl query type for BookCategoryId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBookCategoryId extends BeanPath<BookCategoryId> {

    private static final long serialVersionUID = -2006740714L;

    public static final QBookCategoryId bookCategoryId = new QBookCategoryId("bookCategoryId");

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public QBookCategoryId(String variable) {
        super(BookCategoryId.class, forVariable(variable));
    }

    public QBookCategoryId(Path<? extends BookCategoryId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookCategoryId(PathMetadata metadata) {
        super(BookCategoryId.class, metadata);
    }

}

