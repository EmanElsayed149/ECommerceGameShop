package com.gameshop.gameservice.controllers;

import com.gameshop.gameservice.models.Game;
import com.gameshop.gameservice.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    // GET /games
    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // POST /games  (body: JSON of Game without id)
    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    // DELETE /games/{id}
    @DeleteMapping("/{id}")
    public String deleteGame(@PathVariable Long id) {
        if (!gameRepository.existsById(id)) return "Game not found!";
        try{
            gameRepository.deleteById(id);
            return "Game deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
