package src.kr.ac.jbnu.se.tetris;


public interface BlockGenerator {

    public abstract Tetrominoes generateTetrominoes();
    public abstract Tetrominoes getFirstNextTetrominoes();
    public abstract Tetrominoes getSecondNextTetrominoes();
    public abstract Tetrominoes getThirdnextTetrominoes();

}
