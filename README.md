# Tic Tac toe Game

Consist of 3 API

Method: POST
Path : /new
Request Param: size (its to determine size of the board)
Response: String of GameID (UUID)

```
curl --location --request POST 'http://localhost:8080/new?size=3' \
--header 'Authorization: Basic am9objoxMjM0NQ=='
```


Method: GET
Path: /{gameId} -> game id is uuid response on /new api
Response: String[][] (its represent grid of board)

```
curl --location 'http://localhost:8080/7eae3a70-e621-4de7-a096-2f782472b508/' \
--header 'Authorization: Basic am9objoxMjM0NQ=='
```

Method: PUT
Path: /{gameId}/move
RequestParam


```
curl --location 'http://localhost:8080/7eae3a70-e621-4de7-a096-2f782472b508/move?row=0&col=2' \
--header 'Authorization: Basic am9objoxMjM0NQ=='
```
