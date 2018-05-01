package core.renderer;

public interface Vectors {

    static Vector3 scale(Vector3 v1, Vector3 v2) {
        return new Vector3(new Point3(v1.getPointA().getPosX()*v2.getUnitVector().getPointB().getPosX(), v1.getPointA().getPosY()*v2.getUnitVector().getPointB().getPosY(), v1.getPointA().getPosZ()*v2.getUnitVector().getPointB().getPosZ()), new Point3(v1.getPointB().getPosX()*v2.getUnitVector().getPointB().getPosX(), v1.getPointB().getPosY()*v2.getUnitVector().getPointB().getPosY(), v1.getPointB().getPosZ()*v2.getUnitVector().getPointB().getPosZ()));
    }

    static double dotProduct(Vector3 v1, Vector3 v2) {
        Vector3 temp = scale(v1, v2);
        return ((temp.getPointB().getPosX() - temp.getPointA().getPosX())+(temp.getPointB().getPosY() - temp.getPointA().getPosY())+(temp.getPointB().getPosZ() - temp.getPointA().getPosZ()));
    }

    //static Vector3 translate(Vector3 v1, Vector3 v2) {
   //     return new Vector3(v1.getPosX() + v2.getPosX(), v1.getPosY() + v2.getPosY(), v1.getPosZ() + v2.getPosZ());
    //}

}
