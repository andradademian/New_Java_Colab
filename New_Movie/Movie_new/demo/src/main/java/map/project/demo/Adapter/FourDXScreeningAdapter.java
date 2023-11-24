package map.project.demo.Adapter;

import map.project.demo.Domain.Screening4DX;

public class FourDXScreeningAdapter implements ScreeningPlayer {
    private final Screening4DX screening4DX;

    public FourDXScreeningAdapter(Screening4DX screening4DX) {
        this.screening4DX = screening4DX;
    }

    @Override
    public void play() {
        screening4DX.play4DX();
    }
}
