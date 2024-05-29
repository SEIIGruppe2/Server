package at.aau.se2.handler.game.subhandlers;

public class CardAttackMonster /*implements ActionHandler*/ {
   /* // TODO: Umlagern in PlayerAttack und erstellen von einem Service und einer DTO
    private static final Logger logger = Logger.getLogger(GameRoundHandler.class.getName());
    private String monsterId;
    private String lifepoints;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try{
            String cardid = msg.path("cardid").asText();
            this.monsterId = msg.path("monsterid").asText();
            System.out.println(msg.toString());
            removeCard(Integer.parseInt(cardid), UtilityMethods.findPlayer(session, lobby).getCards());
            List<Monster> monsters =lobby.getGameState().getMonsters();
            Monster m = findmonster(Integer.parseInt(monsterId), monsters);
            this.lifepoints = String.valueOf(decreaseLifePoints(m));
            if(lifepoints.equals("-1")){
                monsters.remove(m);
            }
            for(Monster a: monsters){

                System.out.println(String.valueOf(a.getId()));
                System.out.println(a.getLifepoints());
            }
            updateOtherUser(lobby);


        }

        catch(PlayerNotFoundException p){
            logi("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        } catch (IOException e) {
            logger.severe("Failed to send cardattackmessage: " + e.getMessage());
        }
    }

    private void removeCard(int id, List<Actioncard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if ( cards.get(i).getId()==id) {
                cards.remove(cards.get(i));
            }
        }
    }
    private Monster findmonster(int id, List<Monster> monsters){
        for(Monster m: monsters){
            if(m.getId()==id){
                return m;
            }
        }
    return null;
    }
    private int decreaseLifePoints(Monster m){
        int currentlifepoints = m.getLifepoints();
        if(currentlifepoints == 1){

            return -1;
        }else{
            m.setLifepoints(currentlifepoints-1);
            return currentlifepoints-1;
        }
    }

    public void updateOtherUser(Lobby lobby) throws IOException {
        List<Player> players=lobby.getPlayers();
        ObjectNode messageNode = objectMapper.createObjectNode();

        messageNode.put("type", "CARD_ATTACK_MONSTER");
        messageNode.put("monsterid", monsterId);
        messageNode.put("lifepoints", lifepoints);
        try {
            for (Player a : players) {
                a.getSession().sendMessage(new TextMessage(messageNode.toString()));
            }
        }catch (IOException e) {
            logger.severe("Failed to send cardattackmessage: " + e.getMessage());
        }
    }

*/
}