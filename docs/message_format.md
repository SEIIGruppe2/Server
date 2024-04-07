# Messaging Format for Client-Server Com

* JSON als Dateiformat
* Basic-Format
```json
{
  "type": "TYPANGABE",
  "typspezifische Infos" : ""
}
```
## `type PLAYER_ATTACK` 
```json
{
  "type": "PLAYER_ATTACK",
  "monsterid": "id",
  "cardTypePlayed": "name"
}
```

## `type MONSTER_ATTACK`
```json
{
  "type": "MONSTER_ATTACK",
  "monsterid": "id"
}
```

## `type DRAW_CARD`
```json
{
  "type": "DRAW_CARD"
}
```
## `type SWITCH_CARDS_DECK`
```json
{
  "type": "SWITCH_CARDS_DECK",
  "card": "Karte die getauscht wird"
}
```

## `type SWITCH_CARDS_PLAYER`
```json
{
  "type": "SWITCH_CARDS_PLAYER",
  "switchedWith": "username",
  "cardGiven": "Karte die abgegeben wird",
  "cardGotten": "Karte die bekommen wird"
}
```