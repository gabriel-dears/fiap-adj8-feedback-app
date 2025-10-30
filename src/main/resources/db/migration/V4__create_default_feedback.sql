INSERT INTO feedback (id, student_id, lesson_id, comment, rating, urgent, date)
VALUES ('eeee5555-5555-5555-5555-eeeeeeeeeeee',
        '11111111-1111-1111-1111-111111111111',
        '4b3e2d1c-9a8b-7c6d-5e4f-3a2b1c0d9e8f',
        'Persistent storage concept is tricky, but the lesson simplified it well.',
        'FOUR',
        false,
        NOW() - INTERVAL '4 days');
