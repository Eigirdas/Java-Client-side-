import java.util.*;

public class Bank {
    private Map<Integer, Player> players = new TreeMap<>();


    public Player addPlayer(int id)
    {
        Player player = new Player(id);
        players.put(id, player);
        return player;
    }

    public void removePlayer(int id)
    {   players.get(id).setBall(false);
        players.remove(id);

        ArrayList<Integer> keysAsArray = new ArrayList<Integer>(players.keySet());
        Random r = new Random();
        Player p = players.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
        p.setBall(true);
        System.out.println("The ball was passed to:" + p.getPlayerId());


    }

    public ArrayList<Player> getListOfPlayers() {
        ArrayList<Player> result = new ArrayList<>();

        for (Player player : players.values()){
            result.add(player);
        }


        return result;
    }


    public void transfer(int from, int to) {
        synchronized (players)
        {
            for (Map.Entry<Integer, Player> entry : players.entrySet()){
                int id = entry.getKey();
                Player cur = entry.getValue();
                if (id == from && cur.getDoesHaveBall() == true){
                    cur.setBall(false);
                    for (Map.Entry<Integer, Player> entryTo : players.entrySet()){
                        int idTo = entryTo.getKey();
                        Player curTo = entryTo.getValue();
                        if (idTo == to){
                            curTo.setBall(true);
                        }
                    }
                }
            }

        }
    }


    public int printBallHolder(){

        int id = 0;
        for (Map.Entry<Integer, Player> entry : players.entrySet()){
            Player cur = entry.getValue();
            if (cur.getDoesHaveBall()){
                id = cur.getPlayerId();
                return id;
            }
        }
        return id;

    }
}
