package core.renderer;

public interface Vectors {

    static Vector3 scale(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.getPosX() * v2.getPosX(), v1.getPosY() * v2.getPosY(), v1.getPosZ() * v2.getPosZ());
    }

    static Vector3 translate(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.getPosX() + v2.getPosX(), v1.getPosY() + v2.getPosY(), v1.getPosZ() + v2.getPosZ());
    }

}
