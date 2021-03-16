package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadJSON {

    @SuppressWarnings("unchecked")
    public  ArrayList<Character> readCharacters()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<Character> charactersColletion=new ArrayList<>();

        try (FileReader reader = new FileReader("characters.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray characterList = (JSONArray) obj;
            System.out.println(characterList);

            //Iterate over employee array
            characterList.forEach( character -> parseCharacterObject( (JSONObject) character ) );

           charactersColletion=  new Gson().fromJson(characterList.toString(), new TypeToken<List<Character>>(){}.getType());

            System.out.println("caractere "+charactersColletion.get(0).getName());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

       return charactersColletion;
    }
    @SuppressWarnings("unchecked")
    public  ArrayList<Planet> readPlantes()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<Planet> planetsColletion=new ArrayList<>();
        try (FileReader reader = new FileReader("planets.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray planetList = (JSONArray) obj;
            JSONArray modifiersList = (JSONArray) obj;

            System.out.println(planetList);
            //Iterate over employee array
            planetList.forEach( planet-> parsePlanetObject( (JSONObject) planet) );

           planetsColletion=  new Gson().fromJson(planetList.toString(), new TypeToken<List<Planet>>(){}.getType());

            System.out.println("obiecte  \n"+planetsColletion.get(0).getModifiers().getHeroAttackModifier());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  planetsColletion;
    }

    private static void parseCharacterObject(JSONObject character)
    {
        //Get character object within list
        JSONObject characterObject = (JSONObject) character;

        long id = (Long) characterObject.get("id");
        System.out.println(id);

        String name = (String) characterObject.get("name");
        System.out.println(name);

        String description = (String) characterObject.get("description");
        System.out.println(description);

        long attack = (Long) characterObject.get("attack");
        System.out.println(attack);

        long health = (Long) characterObject.get("health");
        System.out.println(health);

        boolean isVillain = (Boolean) characterObject.get("isVillain");
        System.out.println(isVillain);
        System.out.println("\n \n");

    }

    private static void parsePlanetObject(JSONObject planet)
    {
        //Get character object within list
        JSONObject planetObject = (JSONObject) planet;

        long id = (Long) planetObject.get("id");
        System.out.println(id);

        String name = (String) planetObject.get("name");
        System.out.println(name);

        String description = (String) planetObject.get("description");
        System.out.println(description);


        // getting modifiers
        Map address = ((Map)planetObject.get("modifiers"));

        // iterating address Map
        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }
}





