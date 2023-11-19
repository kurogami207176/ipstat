WITH counts AS (
    SELECT ip, COUNT(ip) as cnt
    FROM logs
    GROUP BY ip
)

SELECT ip, cnt
FROM counts
ORDER BY cnt DESC, ip ASC
LIMIT 3;