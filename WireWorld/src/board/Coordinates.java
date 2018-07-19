package board;

class Coordinates {
    private int x;
    private int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void applyOrientation(Orientation orientation) {
        switch (orientation) {
            case R:
                break;
            case U:
                swapXY();
                this.x *= -1;
                break;
            case D:
                swapXY();
                this.y *= -1;
                break;
            case L:
                this.x *= -1;
                break;

        }
    }

    private void swapXY() {
        int temp = this.x;
        this.x = this.y;
        this.y = temp;
    }


}