-- ============================================================
-- Sample Feedbacks
-- ============================================================

INSERT INTO feedback (id, student_id, lesson_id, comment, rating, urgent, date)
VALUES
-- 1
('aaaa1111-1111-1111-1111-aaaaaaaaaaaa',
 '11111111-1111-1111-1111-111111111111',
 'c6b3a2b1-1f3d-4a8e-b5e7-7fbe7f2b8121',
 'Very clear introduction to Kubernetes basics. Helped me understand cluster concepts easily.',
 'FIVE',
 false,
 NOW()),

-- 2
('bbbb2222-2222-2222-2222-bbbbbbbbbbbb',
 '11111111-1111-1111-1111-111111111111',
 '1fce23b2-92a5-45f6-8e4a-22a1d91c34d9',
 'Good explanation of Pods and containers, but could include more YAML examples.',
 'FOUR',
 false,
 NOW() - INTERVAL '1 day'),

-- 3
('cccc3333-3333-3333-3333-cccccccccccc',
 '11111111-1111-1111-1111-111111111111',
 'e8d1f5c4-7122-4d1b-8e5c-0a9b1b9e3a99',
 'Struggled a bit with networking concepts, maybe add a visual diagram?',
 'THREE',
 true,
 NOW() - INTERVAL '2 days'),

-- 4
('dddd4444-4444-4444-4444-dddddddddddd',
 '11111111-1111-1111-1111-111111111111',
 'df3c1a9e-3b1a-4d89-9a9d-2c6d5b7a8f42',
 'ReplicaSets were new to me, but the examples made it clear. Thanks!',
 'FIVE',
 false,
 NOW() - INTERVAL '3 days'),

-- 5
('eeee5555-5555-5555-5555-eeeeeeeeeeee',
 '11111111-1111-1111-1111-111111111111',
 '4b3e2d1c-9a8b-7c6d-5e4f-3a2b1c0d9e8f',
 'Persistent storage concept is tricky, but the lesson simplified it well.',
 'FOUR',
 false,
 NOW() - INTERVAL '4 days');
