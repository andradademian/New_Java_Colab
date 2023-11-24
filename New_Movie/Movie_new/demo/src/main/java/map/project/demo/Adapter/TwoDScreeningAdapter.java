package map.project.demo.Adapter;

import map.project.demo.Domain.Screening2D;

public class TwoDScreeningAdapter implements ScreeningPlayer {
    private final Screening2D screening2D;

    public TwoDScreeningAdapter(Screening2D screening2D) {
        this.screening2D = screening2D;
    }

    @Override
    public void play() {
        screening2D.play2D();
    }
}
