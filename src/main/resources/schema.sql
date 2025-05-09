DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id UUID NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('USER', 'ADMIN')),
    CONSTRAINT pk_users_id PRIMARY KEY (id),
    CONSTRAINT unique_email UNIQUE (email)
);

Drop table if exists pictures;
-- Create pictures table
CREATE TABLE pictures (
    id UUID NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    file_path VARCHAR(255) NOT NULL,
    width INT NOT NULL,
    height INT NOT NULL,
    upload_time TIMESTAMP NOT NULL,
    user_id UUID,
    CONSTRAINT pk_pictures_id PRIMARY KEY (id)
);

-- Add foreign key constraint
ALTER TABLE pictures
ADD CONSTRAINT fk_picture_user_id
FOREIGN KEY (user_id) REFERENCES users(id);

-- Add category check constraint
ALTER TABLE pictures ADD CONSTRAINT chk_category CHECK (category IN ('LIVING_THING', 'MACHINE', 'NATURE'));

-- Add status check constraint
ALTER TABLE pictures ADD CONSTRAINT chk_status CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED'));