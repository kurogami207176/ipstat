CREATE TABLE logs (
    ip CHARACTER,
    log_date TIMESTAMP WITH TIME ZONE,
    http_operation CHARACTER,
    url CHARACTER,
    http_version CHARACTER,
    status_code INTEGER,
    bytes_downloaded BIGINT
);