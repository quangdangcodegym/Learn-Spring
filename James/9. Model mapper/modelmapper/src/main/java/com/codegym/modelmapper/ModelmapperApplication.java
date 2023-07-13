package com.codegym.modelmapper;

import com.codegym.modelmapper.model.GameDTO;
import com.codegym.modelmapper.model.Player;
import com.codegym.modelmapper.model.PlayerDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ModelmapperApplication
		implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ModelmapperApplication.class, args);
	}
	@Override
	public void run(String... args) {
		System.out.println("aaaaaaaaaaaaaaaaaa");

		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setName("Quang Dang");
		playerDTO.setId(1L);

		List<GameDTO> gameDTOs = new ArrayList<>();
		gameDTOs.add(new GameDTO(5L, "Quang CR5"));
		gameDTOs.add(new GameDTO(6L, "Quang CR6"));

		playerDTO.setGames(gameDTOs);
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Player player = mapper.map(playerDTO, Player.class);

		System.out.println(player);



	}
}
