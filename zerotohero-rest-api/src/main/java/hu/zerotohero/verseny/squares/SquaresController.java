package hu.zerotohero.verseny.squares;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/squares")
public class SquaresController {

    @Autowired
    private SquareCounter squareCounter;

    @PostMapping(value = "/getNumberOfSquares", consumes = "application/json")
    public Integer getNumberOfSquares(@RequestBody List<Point> listOfPoints) {
        return squareCounter.count(listOfPoints);
    }
}
