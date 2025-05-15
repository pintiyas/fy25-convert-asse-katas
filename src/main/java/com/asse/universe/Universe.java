package com.asse.universe;

import com.asse.plateau.Plateau;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Universe {
    private List<Plateau> plateaus = new ArrayList<>();

    public void addPlateau(Plateau plateau) {
        if (plateaus.contains(plateau)) {
            throw new IllegalArgumentException("Plateau already registered");
        }
        plateaus.add(plateau);
    }

    public Plateau getPlateau(String name) {
        return plateaus.stream()
                .filter(plateau -> plateau.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Plateau not found"));
    }
}
