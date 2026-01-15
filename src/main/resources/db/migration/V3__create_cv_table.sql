-- Create CV table
CREATE TABLE cv_entries (
                            id BIGINT IDENTITY(1,1) PRIMARY KEY,
                            full_name VARCHAR(100) NOT NULL,
                            title VARCHAR(200) NULL,
                            email VARCHAR(500) NULL,
                            phone VARCHAR(50) NULL,
                            location VARCHAR(500) NULL,
                            linkedin_url VARCHAR(500) NULL,
                            github_url VARCHAR(500) NULL,
                            website_url VARCHAR(500) NULL,
                            summary VARCHAR(MAX) NULL,
    skills VARCHAR(MAX) NULL,
    experience VARCHAR(MAX) NULL,
    education VARCHAR(MAX) NULL,
    certifications VARCHAR(MAX) NULL,
    profile_photo_url VARCHAR(500) NULL,
    is_active BIT NOT NULL DEFAULT 0,
    created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
    updated_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

-- Insert sample CV entry
INSERT INTO cv_entries (full_name, title, email, phone, location, summary, skills, is_active)
VALUES (
           'Kanishka Fernando',
           'Information Systems Engineering Student',
           'kanishkaf18@gmail.com',
           '0704606206',
           'Colombo, Sri Lanka''',
           'Passionate Information Systems Engineering student at SLIIT with strong skills in full-stack development.',
           'Java, Spring Boot, React, SQL Server, HTML/CSS, JavaScript',
           1
       );