package temporaire;

public class CameraObsolete {
	private MondeObsolete parent;
	private CameraObsolete soeur;
	private double camX, camY;
	private boolean duo;
	
	public CameraObsolete(MondeObsolete parent, CameraObsolete soeur, boolean duo){//Camera principale
		this.parent = parent;
		this.soeur  = soeur;
		this.duo    = duo;
		this.camX   = 0;
		this.camY   = 0;

	}
	
	public CameraObsolete(MondeObsolete parent){
		this.parent = parent;
		soeur       = null;
		this.camX   = 1;
		this.camY   = 1;
	}
	
	public void update(){
		if (duo) {
			if(distance(parent.getJoueurs()[0].getPositionX(),parent.getJoueurs()[1].getPositionX())<(40)//si les parent.getJoueurs() sont dans la zone acceptable
			&& distance(parent.getJoueurs()[0].getPositionY(),parent.getJoueurs()[1].getPositionY())<(20)){
				//scinder cameras
				double positionCentraleX = (parent.getJoueurs()[0].getPositionX()+parent.getJoueurs()[1].getPositionX())/2.0;
				double positionCentraleY = (parent.getJoueurs()[0].getPositionY()+parent.getJoueurs()[1].getPositionY())/2.0;
				
				this.camX  = positionCentraleX; //- parent.getWidth()/4.0;
				soeur.camX = positionCentraleX; //+ parent.getWidth()/4.0;
				
				this.camY  = positionCentraleY;
				soeur.camY = positionCentraleY;
			}else if(distance(this.camX, this.camY,parent.getJoueurs()[0].getPositionX(), parent.getJoueurs()[0].getPositionY())//si la camera principale est plus proche du joueur 1
				   <=distance(this.camX, this.camY,parent.getJoueurs()[1].getPositionX(), parent.getJoueurs()[1].getPositionY())){//mettre camera 1 sur joueur 1, cam2 sur j2
				this.camX = parent.getJoueurs()[0].getPositionX();
				this.camY = parent.getJoueurs()[0].getPositionY();
				
				soeur.camX = parent.getJoueurs()[1].getPositionX();
				soeur.camY = parent.getJoueurs()[1].getPositionY();
			}else{//mettre camera 2 sur joueur 1, cam1 sur j2
				this.camX = parent.getJoueurs()[1].getPositionX();
				this.camY = parent.getJoueurs()[1].getPositionY();
				
				soeur.camX = parent.getJoueurs()[0].getPositionX();
				soeur.camY = parent.getJoueurs()[0].getPositionY();
			}
		} else {
			this.camX = parent.getJoueurs()[0].getPositionX();
			this.camY = parent.getJoueurs()[0].getPositionY();			
		}
	}	

	public double distance(double a1, double a2){
		return Math.abs(a2-a1);
	}
	
	public double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
	}

	public double getCamX() {
		return camX;
	}

	public double getCamY() {
		return camY;
	}

	@Override
	public String toString() {
		return "Camera [camX=" + camX + ", camY=" + camY + "]";
	}
}
