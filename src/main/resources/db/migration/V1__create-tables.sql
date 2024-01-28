create table tbl_movies (
                            id_movie binary(255) not null,
                            availability bit not null,
                            duration integer not null,
                            name varchar(255) not null,
                            parental_rating varchar(255) not null,
                            synopsis varchar(255) not null,
                            year_launch date not null,
                            code varchar(255) not null,
                            primary key (id_movie)
);

create table tbl_genre (
                           genre_id binary(255) not null,
                           name varchar(255) not null,
                           primary key (genre_id)
);

create table tbl_movie_genre (
                                 movie_id binary(255) not null,
                                 genre_id binary(255) not null,
                                 primary key (movie_id, genre_id)
);
