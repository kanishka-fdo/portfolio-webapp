-- Rename is_active column to active in cv_entries table
EXEC sp_rename 'cv_entries.is_active', 'active', 'COLUMN';