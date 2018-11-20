# URL Shortener Using SpringBoot & Redis

## Dev environment - MacOS
1 Install Redis on Mac
1) sudo chown -R $(whoami) /usr/local/share/man/man8
2) brew install redis
3) redis-server

## How to test
### Test with PostMan
1) With PostMan, POST /rest/url with body as long_url_string to create the short url from long url
2) GET /rest/url/{id} - For retrieving the long URL from short id
### Test with Redis
1) In terminal, "redis-cli"
2) Get {id}, eg, "get 7c66254a"


