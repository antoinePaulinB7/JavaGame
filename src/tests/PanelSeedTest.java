package tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import physique.ModelePhysique;
import procedural.Seed;

public class PanelSeedTest extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private ModelePhysique modele;
	private Seed seed;
	private Color couleurTemp;
	private double largeur = 450, hauteur = 450, horizontal = 0, vertical = 0;
	private Path2D.Double axes;
	private boolean animationEnCours;
	private int FPS = 60;
	private Rectangle2D.Double[] temp = new Rectangle2D.Double[4];
	private int[][] cadran = new int[][]{{1,1},{-1,1},{-1,-1},{1,-1}};
	private int x, y;
	private boolean biome = false;
	private boolean danger = false;
	private boolean hauteurBool = false;
	private boolean axesBool = false;
	private boolean loot = false;
	private boolean dispo = false;

	/**
	 * Create the panel.
	 */
	public PanelSeedTest(Seed seed) {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.white);
		this.seed = seed;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		modele = new ModelePhysique(getWidth(), getHeight(),largeur);
		AffineTransform mat = modele.getMatMC();
		mat.scale(1, -1);
		mat.translate((getWidth()/2)/modele.getPixelsParUniteX()+horizontal, -(getHeight()/2)/modele.getPixelsParUniteY()+vertical);
		if(biome||loot||danger||hauteurBool||dispo){
			for(int cpt3 = 0; cpt3<=largeur/4; cpt3++){
				for(int cpt4 = 0; cpt4<=largeur/4; cpt4++){
					for(int i = 0; i<4; i++){
						x = cpt3*cadran[i][0];
						y = cpt4*cadran[i][1];

						temp[i] = new Rectangle2D.Double(x*4-2, y*4-2, 4, 4);

						seed.calculerParametres(x, y);
						System.out.println(seed.getDispoPlateformes());
						int R = 0, G = 0, B = 0;

						if(biome){
							G = 13*seed.getBiome()%255;
						}
						if(dispo){
							R = Math.min(25*seed.getDispoPlateformes(),255);
							G = Math.min(25*seed.getDispoPlateformes(),255);
						}
						if(loot){
							B = Math.min(25*seed.getLoot(),255);
							//R = Math.min(25*seed.getLoot(),255);
						}
						if(danger){
							R = (Math.min(Math.abs(15*seed.getDanger()),255));
						}
						if(hauteurBool){
							R = Math.min(Math.abs(15*seed.getHauteur()),255);
							B = Math.min(Math.abs(15*seed.getHauteur()),255);
							couleurTemp = new Color(R,G,0);

							if(y<0){
								couleurTemp = new Color(0,G,B);
							}
							if(y<0){
								couleurTemp = new Color(0,G,B);
							}
						}
						if(!hauteurBool)couleurTemp = new Color(R,G,B);




						g2d.setColor(couleurTemp);
						g2d.fill(mat.createTransformedShape(temp[i]));

						g2d.setColor(Color.white);

						//System.out.println(R+" "+G+" "+B);
					}
				}
			}
		}else{
			g2d.drawRect(0, 0, getWidth(), getHeight());
		}
		if(axesBool ){
			axes = new Path2D.Double();
			for(double cpt = 0;cpt<largeur;cpt+=4){			
				axes.moveTo(cpt*modele.getPixelsParUniteX()+getWidth()/2+2*horizontal, getHeight());
				axes.lineTo(cpt*modele.getPixelsParUniteX()+getWidth()/2+2*horizontal, 0);

				axes.moveTo(-cpt*modele.getPixelsParUniteX()+getWidth()/2+2*horizontal, getHeight());
				axes.lineTo(-cpt*modele.getPixelsParUniteX()+getWidth()/2+2*horizontal, 0);
			}

			for(double cpt2 = 0;cpt2<hauteur;cpt2+=4){
				axes.moveTo(0,cpt2*modele.getPixelsParUniteY()+getHeight()/2+2*vertical);
				axes.lineTo(getWidth(),cpt2*modele.getPixelsParUniteY()+getHeight()/2+2*vertical);

				axes.moveTo(0,-cpt2*modele.getPixelsParUniteY()+getHeight()/2+2*vertical);
				axes.lineTo(getWidth(),-cpt2*modele.getPixelsParUniteY()+getHeight()/2+2*vertical);
			}
			g2d.setColor(Color.black);
			g2d.draw(axes);
		}
	}

	@Override
	public void run() {
		while(animationEnCours){
			repaint();
			try {
				Thread.sleep((long)(1000/FPS ));				
			}
			catch (InterruptedException e) {
				System.out.println("Erreur pendant le sleep de l'animation");
			}
		}

	}

	public void demarrer(){
		if (!animationEnCours) { 
			System.out.println("Animation démarrée");
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			animationEnCours = true;
		}
	}

	public double getHorizontal(){
		return horizontal;
	}

	public void setHorizontal(double horizontal){
		this.horizontal = horizontal;
	}

	public double getVertical(){
		return vertical;
	}

	public void setVertical(double vertical){
		this.vertical = vertical;
	}

	public Seed getSeed() {
		return seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public boolean isBiome() {
		return biome;
	}

	public void setBiome(boolean biome) {
		this.biome = biome;
	}

	public boolean isDanger() {
		return danger;
	}

	public void setDanger(boolean danger) {
		this.danger = danger;
	}

	public boolean isHauteurBool() {
		return hauteurBool;
	}

	public void setHauteurBool(boolean hauteurBool) {
		this.hauteurBool = hauteurBool;
	}

	public boolean isAxes(){
		return axesBool;
	}

	public void setAxesBool(boolean axesBool){
		this.axesBool = axesBool; 
	}

	public boolean isLoot() {
		return loot;
	}

	public void setLoot(boolean loot) {
		this.loot = loot;
	}

	public boolean isDispoPlateformes() {
		return dispo;
	}

	public void setDispoPlateformes(boolean d) {
		this.dispo = d;
		System.out.println("Setté");
		System.out.println(this.dispo);
	}
}
