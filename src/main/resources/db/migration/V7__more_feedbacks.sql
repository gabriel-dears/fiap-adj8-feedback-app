-- ============================================================
-- New Lessons (hardcoded IDs, not overlapping existing ones)
-- ============================================================
INSERT INTO lesson (id, name, description)
VALUES
    ('f1111111-1111-1111-1111-111111111111', 'Kubernetes Architecture', 'Understand the core components of Kubernetes and how they work together to manage containerized applications.'),
    ('f2222222-2222-2222-2222-222222222222', 'Managing Pods', 'Learn how to create, configure, and manage Pods, the basic unit of deployment in Kubernetes.'),
    ('f3333333-3333-3333-3333-333333333333', 'Kubernetes Services', 'Explore Service types in Kubernetes and how they allow communication between Pods and external clients.'),
    ('f4444444-4444-4444-4444-444444444444', 'Deployments & Scaling', 'Dive into Deployments, ReplicaSets, and scaling strategies for resilient applications.'),
    ('f5555555-5555-5555-5555-555555555555', 'ConfigMaps & Secrets', 'Manage configuration data and sensitive information securely in Kubernetes using ConfigMaps and Secrets.'),
    ('f6666666-6666-6666-6666-666666666666', 'Persistent Storage', 'Learn how to use Volumes, PersistentVolumes, and PersistentVolumeClaims to manage data in Kubernetes.'),
    ('f7777777-7777-7777-7777-777777777777', 'Helm Charts', 'Get started with Helm to package, deploy, and manage applications on Kubernetes clusters.'),
    ('f8888888-8888-8888-8888-888888888888', 'Monitoring & Logging', 'Implement monitoring and logging for your Kubernetes cluster using Prometheus, Grafana, and Fluentd.');

-- ============================================================
-- Feedbacks (3 per student, for first 3 new lessons, date = today)
-- ============================================================
INSERT INTO feedback (id, student_id, lesson_id, comment, rating, urgent, date)
VALUES
-- Student 1
('fb111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', 'f1111111-1111-1111-1111-111111111111', 'Great overview of Kubernetes architecture.', 'FIVE', false, NOW()),
('fb111112-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', 'f2222222-2222-2222-2222-222222222222', 'Pods section is very clear.', 'FOUR', false, NOW()),
('fb111113-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', 'f3333333-3333-3333-3333-333333333333', 'Services explanation is very practical.', 'FIVE', true, NOW()),

-- Student 2
('fb222211-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333335', 'f1111111-1111-1111-1111-111111111111', 'Architecture concepts explained well.', 'FIVE', false, NOW()),
('fb222212-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333335', 'f2222222-2222-2222-2222-222222222222', 'Pods examples are easy to follow.', 'FOUR', false, NOW()),
('fb222213-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333335', 'f3333333-3333-3333-3333-333333333333', 'Services section is helpful.', 'FIVE', true, NOW()),

-- Student 3
('fb333311-3333-3333-3333-333333333335', '44444444-4444-4444-4444-444444444446', 'f1111111-1111-1111-1111-111111111111', 'Clear and concise Kubernetes architecture.', 'FIVE', false, NOW()),
('fb333312-3333-3333-3333-333333333335', '44444444-4444-4444-4444-444444444446', 'f2222222-2222-2222-2222-222222222222', 'Pods management was easy to understand.', 'FOUR', false, NOW()),
('fb333313-3333-3333-3333-333333333335', '44444444-4444-4444-4444-444444444446', 'f3333333-3333-3333-3333-333333333333', 'Services are well explained.', 'FIVE', false, NOW());
