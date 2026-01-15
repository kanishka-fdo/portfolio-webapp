-- Add missing columns to projects table
ALTER TABLE projects
    ADD subtitle VARCHAR(300) NULL;

ALTER TABLE projects
    ADD featured BIT NOT NULL DEFAULT 0;

ALTER TABLE projects
    ADD image_url VARCHAR(500) NULL;