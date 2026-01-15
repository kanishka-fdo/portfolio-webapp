-- Create CV table
CREATE TABLE cv (
                    id BIGINT IDENTITY(1,1) PRIMARY KEY,

                    full_name       VARCHAR(200) NULL,
                    title           VARCHAR(200) NULL,
                    location        VARCHAR(200) NULL,
                    email           VARCHAR(200) NULL,
                    phone           VARCHAR(50)  NULL,

                    summary         VARCHAR(MAX) NULL,
    skills          VARCHAR(MAX) NULL,
    experience      VARCHAR(MAX) NULL,
    education       VARCHAR(MAX) NULL,
    certifications  VARCHAR(MAX) NULL,

    github_url      VARCHAR(500) NULL,
    linkedin_url    VARCHAR(500) NULL,
    website_url     VARCHAR(500) NULL,

    profile_photo_url VARCHAR(500) NULL,

    is_active BIT NOT NULL DEFAULT 0,

    created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
    updated_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);