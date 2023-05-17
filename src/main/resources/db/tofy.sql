create database tofy;

create table tofy.attraction_description like trip.attraction_description;
insert into tofy.attraction_description select * from trip.attraction_description;

create table tofy.attraction_info like trip.attraction_info;
insert into tofy.attraction_info select * from trip.attraction_info;

create table tofy.gugun like trip.gugun;
insert into tofy.gugun select * from trip.gugun;

create table tofy.sido like trip.sido;
insert into tofy.sido select * from trip.sido;

use tofy;

-- 회원
CREATE TABLE user(
    user_no INT NOT NULL,
    user_nm VARCHAR(30) NOT NULL,
    user_id VARCHAR(70) NOT NULL,
    user_pw VARCHAR(70) NOT NULL,
    email_id VARCHAR(70) NOT NULL,
    email_domain VARCHAR(70) NOT NULL,
    admin_fl CHAR(1) DEFAULT 'N',
    reg_time DATETIME DEFAULT now(),		
    token VARCHAR(1000) NULL
);

-- 회원
ALTER TABLE user
    ADD CONSTRAINT PK_user -- 회원 기본키
    PRIMARY KEY (
    user_no -- 회원번호
    );

ALTER TABLE user
    MODIFY COLUMN user_no INT NOT NULL AUTO_INCREMENT;

-- 여행계획
CREATE TABLE trip_plan (
    trip_plan_no INT NOT NULL,
    user_no INT NOT NULL,
    trip_plan_title VARCHAR(50) NOT NULL,
    trip_plan_content VARCHAR(1000) NOT NULL,
    trip_plan_create DATETIME DEFAULT now(),
    trip_plan_start DATETIME NULL,
    trip_plan_end DATETIME NULL,
    trip_plan_hit INT DEFAULT '0'
);

-- 여행계획
ALTER TABLE trip_plan
    ADD CONSTRAINT PK_trip_plan -- 여행계획 기본키
    PRIMARY KEY (
    trip_plan_no -- 계획번호
    );

ALTER TABLE trip_plan
    MODIFY COLUMN trip_plan_no INT NOT NULL AUTO_INCREMENT;

-- 공지사항
CREATE TABLE notice (
    notice_no INT NOT NULL,
    user_no INT NULL,
    notice_title VARCHAR(100) NULL,
    notice_content VARCHAR(1000) NULL,
    notice_create DATETIME DEFAULT now(),
    notice_hit INT DEFAULT '0'
);

-- 공지사항
ALTER TABLE notice
    ADD CONSTRAINT PK_notice -- 공지사항 기본키
    PRIMARY KEY (
    notice_no -- 공지사항번호
    );

ALTER TABLE notice
    MODIFY COLUMN notice_no INT NOT NULL AUTO_INCREMENT;

-- 자유게시글
CREATE TABLE free_board (
    free_board_no INT NOT NULL,
    user_no INT NULL,
    free_board_create DATETIME DEFAULT now(),
    free_board_title VARCHAR(100) NULL,
    free_board_content VARCHAR(500) NULL,
    free_board_hit INT DEFAULT '0'
);

-- 자유게시글
ALTER TABLE free_board
    ADD CONSTRAINT PK_free_board -- 자유게시글 기본키
    PRIMARY KEY (
    free_board_no -- 자유게시글번호
    );

ALTER TABLE free_board
    MODIFY COLUMN free_board_no INT NOT NULL AUTO_INCREMENT;

-- 자유게시글댓글
CREATE TABLE free_board_comment (
    comment_no INT NOT NULL,
    user_no INT NULL,
    free_board_no INT NULL,
    comment_create DATETIME DEFAULT now(),
    comment_content VARCHAR(500)
);

-- 자유게시글댓글
ALTER TABLE free_board_comment
    ADD CONSTRAINT PK_free_board_comment -- 자유게시글댓글 기본키
    PRIMARY KEY (
    comment_no -- 댓글번호
    );

ALTER TABLE free_board_comment
    MODIFY COLUMN comment_no INT NOT NULL AUTO_INCREMENT;

-- 태그
CREATE TABLE tag (
    tag_no INT NOT NULL,
    tag_nm VARCHAR(20) NULL
);

-- 태그
ALTER TABLE tag
    ADD CONSTRAINT PK_tag -- 태그 기본키
    PRIMARY KEY (
    tag_no -- 태그번호
    );

ALTER TABLE tag
    MODIFY COLUMN tag_no INT NOT NULL AUTO_INCREMENT;

-- 회원_태그선택하다
CREATE TABLE select_tag (
    user_no INT NOT NULL,
    tag_no INT NOT NULL
);

-- 회원_태그선택하다
ALTER TABLE select_tag
    ADD CONSTRAINT PK_select_tag -- 회원_태그선택하다 기본키
    PRIMARY KEY (
    user_no, -- 회원번호
    tag_no   -- 태그번호
    );

-- 회원_월드컵하다
CREATE TABLE trip_worldcup (
    trip_worldcup_no INT NOT NULL,
    user_no INT NOT NULL,
    content_id INT NOT NULL,
    worldcup_create DATETIME DEFAULT now()
);

-- 회원_월드컵하다
ALTER TABLE trip_worldcup
    ADD CONSTRAINT PK_trip_worldcup -- 회원_월드컵하다 기본키
    PRIMARY KEY (
    trip_worldcup_no -- 월드컵번호
    );

ALTER TABLE trip_worldcup
    MODIFY COLUMN trip_worldcup_no INT NOT NULL AUTO_INCREMENT;

-- 월드컵_우승
CREATE TABLE win_cnt_by_tag (
    content_id INT NOT NULL,
    tag_no INT NOT NULL,
    count INT DEFAULT '0'
);

-- 월드컵_우승
ALTER TABLE win_cnt_by_tag
    ADD CONSTRAINT PK_win_cnt_by_tag -- 월드컵_우승 기본키
    PRIMARY KEY (
    content_id, -- 여행지번호
    tag_no      -- 태그번호
    );

-- 여행지리뷰
CREATE TABLE attraction_review (
    attraction_review_no INT NOT NULL,
    content_id INT NULL,
    user_no INT NULL,
    attraction_review_content VARCHAR(500) NULL,
    attraction_review_score INT DEFAULT '0',
    attraction_review_img VARCHAR(500) NULL,
    attraction_review_create DATETIME DEFAULT now()
);

-- 여행지리뷰
ALTER TABLE attraction_review
    ADD CONSTRAINT PK_attraction_review -- 여행지리뷰 기본키
    PRIMARY KEY (
    attraction_review_no -- 리뷰번호
    );

ALTER TABLE attraction_review
    MODIFY COLUMN attraction_review_no INT NOT NULL AUTO_INCREMENT;

-- 계획상세
CREATE TABLE trip_plan_detail (
    trip_plan_no INT NOT NULL,
    content_id INT NULL
);

-- 계획상세
ALTER TABLE trip_plan_detail
    ADD CONSTRAINT PK_trip_plan_detail -- 계획상세 기본키
    PRIMARY KEY (
    trip_plan_no -- 계획번호
    );


-- 여행계획
ALTER TABLE trip_plan
    ADD CONSTRAINT FK_user_TO_trip_plan -- 회원 -> 여행계획
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 공지사항
ALTER TABLE notice
    ADD CONSTRAINT FK_user_TO_notice -- 회원 -> 공지사항
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 자유게시글
ALTER TABLE free_board
    ADD CONSTRAINT FK_user_TO_free_board -- 회원 -> 자유게시글
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 자유게시글댓글
ALTER TABLE free_board_comment
    ADD CONSTRAINT FK_user_TO_free_board_comment -- 회원 -> 자유게시글댓글
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 자유게시글댓글
ALTER TABLE free_board_comment
    ADD CONSTRAINT FK_free_board_TO_free_board_comment -- 자유게시글 -> 자유게시글댓글
    FOREIGN KEY (
    free_board_no -- 자유게시글번호
    )
    REFERENCES free_board ( -- 자유게시글
    free_board_no -- 자유게시글번호
    );

-- 회원_태그선택하다
ALTER TABLE select_tag
    ADD CONSTRAINT FK_user_TO_select_tag -- 회원 -> 회원_태그선택하다
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 회원_태그선택하다
ALTER TABLE select_tag
    ADD CONSTRAINT FK_tag_TO_select_tag -- 태그 -> 회원_태그선택하다
    FOREIGN KEY (
    tag_no -- 태그번호
    )
    REFERENCES tag ( -- 태그
    tag_no -- 태그번호
    );

-- 회원_월드컵하다
ALTER TABLE trip_worldcup
    ADD CONSTRAINT FK_user_TO_trip_worldcup -- 회원 -> 회원_월드컵하다
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 회원_월드컵하다
ALTER TABLE trip_worldcup
    ADD CONSTRAINT FK_attraction_info_TO_trip_worldcup -- 여행지정보 -> 회원_월드컵하다
    FOREIGN KEY (
    content_id -- 여행지번호
    )
    REFERENCES attraction_info ( -- 여행지정보
    content_id -- 여행지번호
    );

-- 월드컵_우승
ALTER TABLE win_cnt_by_tag
    ADD CONSTRAINT FK_attraction_info_TO_win_cnt_by_tag -- 여행지정보 -> 월드컵_우승
    FOREIGN KEY (
    content_id -- 여행지번호
    )
    REFERENCES attraction_info ( -- 여행지정보
    content_id -- 여행지번호
    );

-- 월드컵_우승
ALTER TABLE win_cnt_by_tag
    ADD CONSTRAINT FK_tag_TO_win_cnt_by_tag -- 태그 -> 월드컵_우승
    FOREIGN KEY (
    tag_no -- 태그번호
    )
    REFERENCES tag ( -- 태그
    tag_no -- 태그번호
    );

-- 여행지리뷰
ALTER TABLE attraction_review
    ADD CONSTRAINT FK_attraction_info_TO_attraction_review -- 여행지정보 -> 여행지리뷰
    FOREIGN KEY (
    content_id -- 여행지번호
    )
    REFERENCES attraction_info ( -- 여행지정보
    content_id -- 여행지번호
    );

-- 여행지리뷰
ALTER TABLE attraction_review
    ADD CONSTRAINT FK_user_TO_attraction_review -- 회원 -> 여행지리뷰
    FOREIGN KEY (
    user_no -- 회원번호
    )
    REFERENCES user ( -- 회원
    user_no -- 회원번호
    );

-- 계획상세
ALTER TABLE trip_plan_detail
    ADD CONSTRAINT FK_trip_plan_TO_trip_plan_detail -- 여행계획 -> 계획상세
    FOREIGN KEY (
    trip_plan_no -- 계획번호
    )
    REFERENCES trip_plan ( -- 여행계획
    trip_plan_no -- 계획번호
    );

-- 계획상세
ALTER TABLE trip_plan_detail
    ADD CONSTRAINT FK_attraction_info_TO_trip_plan_detail -- 여행지정보 -> 계획상세
    FOREIGN KEY (
    content_id -- 여행지번호
    )
    REFERENCES attraction_info ( -- 여행지정보
    content_id -- 여행지번호
    );