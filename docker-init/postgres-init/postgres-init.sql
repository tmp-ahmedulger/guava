SELECT 'CREATE DATABASE "authentication-server"'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"authentication-server"')\gexec

SELECT 'CREATE DATABASE "parcel-delivery-service"'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '"parcel-delivery-service"')\gexec