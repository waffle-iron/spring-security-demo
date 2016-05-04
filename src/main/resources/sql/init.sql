BEGIN TRANSACTION;

DROP TABLE IF EXISTS "theme" CASCADE;
DROP SEQUENCE IF EXISTS "theme_seq";

DROP TABLE IF EXISTS "word_theme" CASCADE;

DROP TABLE IF EXISTS "word" CASCADE;
DROP SEQUENCE IF EXISTS "word_seq";

DROP TABLE IF EXISTS "user" CASCADE;
DROP SEQUENCE IF EXISTS "user_seq";

DROP TABLE IF EXISTS "user_words" CASCADE;
DROP SEQUENCE IF EXISTS "user_words_seq";

DROP TABLE IF EXISTS "user_word_statistic" CASCADE;
DROP SEQUENCE IF EXISTS "user_word_statistic_seq";

---------------------------------------------------------

CREATE SEQUENCE "theme_seq";
CREATE TABLE "theme" (
  "id"           BIGINT PRIMARY KEY       DEFAULT "nextval"('"theme_seq"'),
  "created_date" TIMESTAMP WITH TIME ZONE DEFAULT "now"(),
  "name"         TEXT NOT NULL,
  -- default or custom
  "holder"       TEXT NOT NULL,
  CONSTRAINT "name_unique" UNIQUE (name)
);

CREATE SEQUENCE "word_seq";
CREATE TABLE "word" (
  "id"           BIGINT PRIMARY KEY       DEFAULT "nextval"('"word_seq"'),
  "created_date" TIMESTAMP WITH TIME ZONE DEFAULT "now"(),
  "value"        TEXT NOT NULL,
  "translate"    JSONB,
  "theme_id"     BIGINT REFERENCES "theme" (id),
  CONSTRAINT "word_unique" UNIQUE (VALUE)
);

CREATE TABLE "word_theme" (
  "word_id"  BIGINT REFERENCES "word" (id),
  "theme_id" BIGINT REFERENCES "theme" (id)
);

CREATE SEQUENCE "user_seq";
CREATE TABLE "user" (
  "id"               BIGINT PRIMARY KEY       DEFAULT "nextval"('"user_seq"'),
  "created_date"     TIMESTAMP WITH TIME ZONE DEFAULT "now"(),
  "login"            TEXT NOT NULL,
  "password"         TEXT NOT NULL,
  "confirm_password" BOOLEAN,
  "email"            TEXT,
  CONSTRAINT "login_unique" UNIQUE (login),
  CONSTRAINT "email_unique" UNIQUE (email)
);

CREATE SEQUENCE "user_word_statistic_seq";
CREATE TABLE "user_word_statistic" (
  "id"            BIGINT PRIMARY KEY       DEFAULT "nextval"('"user_word_statistic_seq"'),
  "created_date"  TIMESTAMP WITH TIME ZONE DEFAULT "now"(),
  -- new, in_progress, learning
  "state"         TEXT NOT NULL
);

CREATE SEQUENCE "user_words_seq";
CREATE TABLE "user_words" (
  "id"           BIGINT PRIMARY KEY       DEFAULT "nextval"('"user_words_seq"'),
  "created_date" TIMESTAMP WITH TIME ZONE DEFAULT "now"(),
  "word_id"      BIGINT REFERENCES "word" ("id"),
  "user_id"      BIGINT REFERENCES "user" ("id"),
  "user_word_statistic_id" BIGINT REFERENCES "user_word_statistic" ("id"),
  CONSTRAINT "word_id_and_use_id" UNIQUE (word_id, user_id)
);

END TRANSACTION;