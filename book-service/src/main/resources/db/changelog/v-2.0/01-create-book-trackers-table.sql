CREATE TABLE book_trackers
(
    id             BIGSERIAL NOT NULL,
    book_id        BIGINT,
    date_borrowed  DATE,
    date_to_return DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);