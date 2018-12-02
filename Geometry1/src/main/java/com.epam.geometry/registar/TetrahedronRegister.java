package com.epam.geometry.registar;

import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Tetrahedron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TetrahedronRegister  implements Observer<Tetrahedron> {
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronRegister.class);

    private static TetrahedronRegister registerory;

    private TetrahedronCalculator  tetrahedronCalculator = new TetrahedronCalculator();

    public static TetrahedronRegister getInstance() {
        if (registerory != null) {
            return registerory;
        }
        registerory = new TetrahedronRegister();
        LOGGER.info("TetrahedronRepository was:" + " is created");
        return registerory;
    }


    public Double getSquare(long id) {
        return square.get(id);
    }

    public Double getVolume(long id) {
        return volume.get(id);
    }

    private Map<Long, Double> square;
    private Map<Long, Double> volume;

    private TetrahedronRegister() {
        square = new HashMap<>();
        volume = new HashMap<>();
    }

    @Override
    public void update(Tetrahedron tetrahedron) {

        square.put(tetrahedron.getID(), tetrahedronCalculator.calculateSquare(tetrahedron));
        volume.put(tetrahedron.getID(), tetrahedronCalculator.calculateVolume(tetrahedron));
        LOGGER.info("TetrahedronRegister is updated");
    }
}

