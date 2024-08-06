-- Veillez à mettre le nom de votre base de donnée crée --

USE `p6-oc-md`;

-- Création de la table "topics" si elle n'existe pas
CREATE TABLE IF NOT EXISTS topics (
                                      id INT PRIMARY KEY,
                                      title VARCHAR(255),
    description TEXT,
    created_at DATE,
    updated_at DATE
    );

-- Insertion ou mise à jour des enregistrements avec des données factices
INSERT INTO topics (id, title, description, created_at, updated_at) VALUES
                                                                        (1, 'Just Say No to More End-to-End', 'A testing strategy that relies heavily on end-to-end tests can cause a lot of issues in reality. While end-to-end tests seem like a good idea in theory, they often lead to delays in bug identification and fixes, as well as flaky test results. Instead, a better approach is to focus on unit tests and integration tests, which are often faster and more reliable. ', CURDATE(), CURDATE()),
                                                                        (2, 'How we tamed Node.js event loop lag: a deepdive', 'Trigger encountered reliability and performance issues in its Node.js application due to event loop lag, which led to high CPU usage and crashes. To fix this, it fixed inefficient code, added pagination, and monitored event loop lag with OpenTelemetry. Moving forward, it will keep an eye on larger payloads, as a learning from this experience.', CURDATE(), CURDATE()),
                                                                        (3, 'SCALE Lang (Website)', 'SCALE is a GPGPU programming toolkit that allows CUDA applications to be natively compiled for AMD GPUs. It does not require the CUDA program or its build system to be modified.', CURDATE(), CURDATE()),
                                                                        (4, 'How Canva collects 25 billion events per day ', 'Canva s product analytics pipeline collects 25 billion events per day with high uptime, using Protobuf to define event schemas and Datumgen to generate code and enforce compatibility rules. The event collection process starts with analytics clients on various platforms, then events are validated and enriched in ingest-workers before being sent to a Kinesis Data Stream (KDS). KDS serves as the primary streaming platform, with SQS as a fallback for overflow protection and failover mode.', CURDATE(), CURDATE()),
                                                                        (5, 'Ruby methods are colorless', 'Ruby methods are "colorless," meaning there s no distinction between synchronous and asynchronous methods, allowing for asynchronous behavior without explicit markers. Ruby achieves this through independent call stacks, enabled by Threads and Fibers, which allow for switching between tasks without blocking the main thread. This concurrency model, similar to Go s, allows for efficient handling of blocking operations like file reading, HTTP calls, and database queries', CURDATE(), CURDATE()),
                                                                        (6, 'How to use Perplexity in your daily workflow ', 'Perplexity is useful thanks to its ability to choose between different AI models and a Pro Search function that asks clarifying questions. It can be integrated into daily workflows for tasks like morning briefings, fact-checking, brainstorming, and writing assistance.', CURDATE(), CURDATE())
    AS new_values
ON DUPLICATE KEY UPDATE
                     title = new_values.title,
                     description = new_values.description,
                     updated_at = new_values.updated_at;
