package map.project.demo.Adapter;

import map.project.demo.Domain.Screening3D;

public class ThreeDScreeningAdapter implements ScreeningPlayer {
    private final Screening3D screening3D;

    public ThreeDScreeningAdapter(Screening3D screening3D) {
        this.screening3D = screening3D;
    }

    @Override
    public void play() {
        screening3D.play3D();
    }
}
