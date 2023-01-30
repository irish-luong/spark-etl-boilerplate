--Insert config: IngesterJob - for input file's paths
INSERT INTO JOB_CONFIG (CLIENT_ID, JOB_NAME, CONFIG_NAME, VALUE)
VALUES ('max', 'ingesterJob', 'inputPath', 'src/main/resources/data/ingestion/');