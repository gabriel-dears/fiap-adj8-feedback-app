-- ============================================================
-- V1__init.sql
-- Initial schema for Feedback Platform
-- ============================================================

-- =========================
-- Table: app_user
-- =========================
CREATE TABLE app_user (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          role VARCHAR(50) NOT NULL
);

-- =========================
-- Table: lesson
-- =========================
CREATE TABLE lesson (
                        id UUID PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT
);

-- =========================
-- Table: feedback
-- =========================
CREATE TABLE feedback (
                          id UUID PRIMARY KEY,
                          student_id UUID NOT NULL,
                          lesson_id UUID NOT NULL,
                          comment TEXT,
                          rating VARCHAR(50),
                          urgent BOOLEAN,
                          date TIMESTAMP WITHOUT TIME ZONE,

                          CONSTRAINT fk_feedback_student
                              FOREIGN KEY (student_id) REFERENCES app_user (id)
                                  ON DELETE CASCADE,

                          CONSTRAINT fk_feedback_lesson
                              FOREIGN KEY (lesson_id) REFERENCES lesson (id)
                                  ON DELETE CASCADE
);

-- Optional: indexes for performance
CREATE INDEX idx_feedback_student ON feedback(student_id);
CREATE INDEX idx_feedback_lesson ON feedback(lesson_id);
