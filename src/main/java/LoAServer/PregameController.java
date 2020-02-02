package LoAServer;

import com.google.gson.Gson;
import eu.kartoffelquadrat.asyncrestlib.ResponseGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;

@RestController
public class PregameController {
    private MasterDatabase masterDatabase = MasterDatabase.getInstance();

    @RequestMapping(method=RequestMethod.POST, value="/login")
    public LoginResponses login(@RequestBody Player p) {
        System.out.println(p.getUsername() + p.getPassword());
        return masterDatabase.getMasterPlayerDatabase().login(p);
    }

    // new controllers here not yet tested
    // missing - choose hero, distribute items
    @RequestMapping(method=RequestMethod.GET, value="/getAllGames")
    public ArrayList<String> getAllGames() {
        return masterDatabase.getMasterGameDatabase().getAllGames();
    }

    @RequestMapping(method=RequestMethod.POST, value="/hostGame") // assume everything is already put in the parameter Game g
    public HostGameResponses hostGame(@RequestBody Game g) {
        return masterDatabase.getMasterGameDatabase().hostGame(g);
    }

    @RequestMapping(method=RequestMethod.POST, value="/{gameName}/{username}/joinGame")
    public JoinGameResponses joinGame(@PathVariable String gameName, @PathVariable String username) {
        return masterDatabase.getMasterGameDatabase().joinGame(gameName, username);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{gameName}/{username}/leavePregame")
    public void leavePregame(@PathVariable String gameName, @PathVariable String username) {
        masterDatabase.getMasterGameDatabase().leavePregame(gameName, username);
        System.out.println(new Gson().toJson(masterDatabase.getMasterGameDatabase()));
    }

    @RequestMapping(method=RequestMethod.GET, value="/{username}/getPregameUpdate")
    public DeferredResult<ResponseEntity<String>> getPregameUpdate(@PathVariable String username) {
        return ResponseGenerator.getAsyncUpdate(5000, masterDatabase.getMasterGameBCM().get(username));
    }

    @RequestMapping(method=RequestMethod.POST, value="/{gameName}/{username}/isReady")
    public IsReadyResponses isRead(@PathVariable String gameName, @PathVariable String username) {
        return masterDatabase.getMasterGameDatabase().playerIsReady(gameName, username);
    }

    @RequestMapping(method=RequestMethod.POST, value="/{gameName}/{username}/selectHero")
    public SelectHeroResponses selectHero(@PathVariable String gameName, @PathVariable String username, @RequestBody Hero hero) {
        return masterDatabase.getMasterGameDatabase().playerSelectHero(gameName, username, hero);
    }
}