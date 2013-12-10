package battlecode.client.viewer.render;

import battlecode.common.*;
import battlecode.client.util.*;
import battlecode.client.viewer.BufferedMatch;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

class DrawHUD {

    private static final int numArchons = 1;
    private static final float slotSize = 0.8f / (numArchons + 1);
    private static final Font footerFont;
	
    private static final ImageFile bg = new ImageFile("art/hud_bg.png");
    private static final ImageFile unitUnder = new ImageFile("art/hud_unit_underlay.png");
    private static final ImageFile gameText = new ImageFile("art/game.png");
    private static ImageFile numberText;
    private static BufferedImage[] numbers;
	private static BufferedMatch match;
	private ImageFile avatar;

    private static final ImageFile rPickaxe = new ImageFile("art/pickaxe.png");
    private static final ImageFile rDefusion = new ImageFile("art/defusion.png");
    private static final ImageFile rVision = new ImageFile("art/vision.png");
    private static final ImageFile rFusion = new ImageFile("art/fusion.png");
    private static final ImageFile rNuke = new ImageFile("art/nuke.png");
    
    private static final RobotType[] drawnTypes = new RobotType[] {
    	RobotType.SOLDIER,
    	RobotType.GENERATOR,
    	RobotType.SUPPLIER,
    	RobotType.ARTILLERY,
    	RobotType.MEDBAY,
    	RobotType.SHIELDS
    };
    private static final ImageFile [][] rImages = new ImageFile[3][6];		

    static {
        numberText = new ImageFile("art/numbers.png");
        numbers = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            try {
                numbers[i] = numberText.image.getSubimage(48 * i, 0, 48, 64);
            } catch (NullPointerException e) {
               // e.printStackTrace();
            }
        }
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,new File("art/computerfont.ttf")).deriveFont(14.f);
		} catch(Exception e) {
			font = new Font("Serif",Font.PLAIN,18);
		}
		footerFont = font;
		
		for (Team t : new Team[]{Team.NEUTRAL, Team.A, Team.B})
    		for (int x=0; x<drawnTypes.length; x++)
    		{
    			RobotType rt = drawnTypes[x];
    			rImages[t.ordinal()][x] = new ImageFile("art/" + rt.toString().toLowerCase() + (t == Team.NEUTRAL ? "0" : (t == Team.A ? "1" : "2")) + ".png");
    		}
    }
    private final DrawState ds;
    private final Team team;
    private final Rectangle2D.Float bgFill = new Rectangle2D.Float(0, 0, 1, 1);
    private float width;
    private float spriteScale;
    private String footerText = "";
    private int points = 0;
    private static final AffineTransform textScale =
            AffineTransform.getScaleInstance(1 / 64.0, 1 / 64.0);
    private static final AffineTransform textScaleSmall =
            AffineTransform.getScaleInstance(1 / 256.0, 1 / 256.0);

    public DrawHUD(DrawState ds, Team team, BufferedMatch match) {
        this.ds = ds;
        this.team = team;
		this.match = match;
        setRatioWidth(2.0f / 9.0f);
    }

    public float getRatioWidth() {
        return width;
    }

    public void setRatioWidth(float widthToHeight) {
        bgFill.width = width = widthToHeight;
        spriteScale = Math.min(slotSize / 2.5f, width / 2);
    }

    public void setPointsText(int value) {
        points = value;
    }

    public void setFooterText(String text) {
        footerText = text;
    }
    // stuff for win display
    int aWins = 0, bWins = 0;

    public void setWins(int a, int b) {
        aWins = a;
        bWins = b;
    }

	public void tryLoadAvatar() {
		if(avatar==null) {
			String teamName = team==Team.A ? match.getTeamA() : match.getTeamB();
			if(teamName!=null) {
				avatar = new ImageFile("avatars/" + teamName+".png");
			}
		}
	}

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.BLACK);
        //g2.fill(bgFill);
        AffineTransform trans = AffineTransform.getScaleInstance(bgFill.width, bgFill.height);
        BufferedImage bgImg = bg.image;
        trans.scale(1.0 / bgImg.getWidth(), 1.0 / bgImg.getHeight());
        g2.drawImage(bgImg, trans, null);
        AffineTransform pushed = g2.getTransform();
        {
            g2.translate(width / 2, 0.9);
            g2.scale(width / 4.5, width / 4.5);
			AffineTransform pushed2 = g2.getTransform();
			tryLoadAvatar();
			if(avatar!=null&&avatar.image!=null && 1 == 0) {
				g2.setTransform(pushed);
				g2.translate(0.5f * (width - spriteScale), 0.5f * (slotSize - spriteScale)+7*slotSize);
				g2.scale(spriteScale,spriteScale);
				g2.translate(-.5,-.5);
				g2.scale(2.0/avatar.image.getWidth(),2.0/avatar.image.getHeight());
				g2.drawImage(avatar.image,null,null);
			} else {
				g2.translate(-1.875, -1);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setFont(footerFont);
				g2.translate(width / 2, .9);
				//g2.scale(width / 4.5, width / 4.5);
				FontMetrics fm = g2.getFontMetrics();
				String teamName;
				double scaleAmount = 4.5;
				if (team == Team.A) {
					g2.setColor(Color.RED);
					teamName = "Team A";
					if (match.getTeamA() != null) {
							teamName = DrawCutScene.getTeamName(match.getTeamA());
							scaleAmount = fm.stringWidth(teamName) / 16.0;
					}
				} else {
					assert team == Team.B;
					g2.setColor(Color.BLUE);
					teamName = "Team B";
					if (match.getTeamB() != null) {
							teamName = DrawCutScene.getTeamName(match.getTeamB());
							scaleAmount = fm.stringWidth(teamName) / 16.0;
					}
				}
				scaleAmount = Math.max(scaleAmount, 4.5);
				g2.scale(width / scaleAmount, width / scaleAmount);
				g2.drawString(teamName, 0, 0);
            }
            g2.setTransform(pushed2);

            if (footerText.startsWith("GAME")) { // Game Number
                g2.translate(-2, 0);
                g2.drawImage(gameText.image, textScale, null);

                // if team A won more than one round, give it a red circle
                if (aWins > 0) {
                    g2.translate(0.f, 1.25f);
                    g2.setColor(Color.RED);
                    g2.fillOval(0, 0, 1, 1);
                    g2.translate(0.f, -1.25f);
                }

                g2.translate(3, 0);
                for (int i = 5; i < footerText.length(); i++) {
                    g2.drawImage(numbers[Integer.decode(footerText.substring(i, i + 1))], textScale, null);
                    g2.translate(0.5, 0);
                }
            } else if (footerText.length() == 4) { // round counter
                // if team B won more than one round, give it a blue circle
                if (bWins > 0) {
                    // damn yangs magic offsets -_-
                    g2.translate(0.75f, 1.25f);
                    g2.setColor(Color.BLUE);
                    g2.fillOval(0, 0, 1, 1);
                    g2.translate(-0.75f, -1.25f);
                }

                g2.translate(-1.5, 0);
                for (int i = 0; i < 4; i++) {
                    g2.drawImage(numbers[Integer.decode(footerText.substring(i, i + 1))], textScale, null);
                    g2.translate(0.75, 0);
                }
            }
        }
        g2.setTransform(pushed);
        g2.translate(0.5f * (width - spriteScale),
                0.5f * (slotSize - spriteScale));
		//System.out.println("drawing");
        try {
        	// TODO
        	// CORY FIX IT
            DrawObject hq = ds.getHQ(team);
						drawRobot(g2,hq);
            drawTeamResource(g2, hq);
        } catch (ConcurrentModificationException e) {
			e.printStackTrace();
        }
    }

	public void drawRobot(Graphics2D g2, DrawObject r) {
    	AffineTransform pushed = g2.getTransform();
        {
        	g2.scale(spriteScale, spriteScale);
            AffineTransform pushed2 = g2.getTransform();
			{
				BufferedImage underImg = unitUnder.image;
				g2.translate(-0.5, -0.5);
				g2.scale(2.0 / underImg.getWidth(), 2.0 / underImg.getHeight());
				g2.drawImage(underImg, null, null);
			}
			g2.setTransform(pushed2);
			if (r!=null)
				r.drawImmediate(g2, false, true);
        }
        g2.setTransform(pushed);
        g2.translate(0, slotSize);
	}
	
	public void drawTeamResource(Graphics2D g2, DrawObject r) {
		if (r==null) return;
    	AffineTransform pushed = g2.getTransform();
        {
        	g2.scale(spriteScale, spriteScale);
            AffineTransform pushed2 = g2.getTransform();
			{
				BufferedImage underImg = unitUnder.image;
				g2.translate(-0.5, -0.5);
				g2.scale(2.0 / underImg.getWidth(), 1.0 / underImg.getHeight());
				if (r.getTeam() == Team.A) g2.setColor(Color.red);
				else g2.setColor(Color.blue);
				double percent = Math.min(ds.getTeamResources(r.getTeam())/200.0, 1.0);
//				System.out.println(percent);
				int height = (int)(underImg.getHeight()*percent);
				g2.fillRect(0, underImg.getHeight()-height, underImg.getWidth(), height);
				
				g2.setTransform(pushed2);
//				if (r!=null)
//					r.drawImmediate(g2, false, true);
				String resource = (int)(ds.getTeamResources(r.getTeam()))+"";
				while (resource.length() < 8) resource = "0"+resource;
				g2.translate(-.3, .5);
	            for (int i = 0; i < 8; i++) {
	                g2.drawImage(numbers[Integer.decode(resource.substring(i, i + 1))], textScaleSmall, null);
	                g2.translate(0.75/4, 0);
	            }
			}
			
			
			g2.setTransform(pushed2);
			g2.translate(-0.5, -2.0);
			int[] counts = ds.getRobotCounts(r.getTeam());

			g2.translate(0.1, 0);
			for (int x=0; x<drawnTypes.length; x++)
			{
				BufferedImage target = rImages[r.getTeam().ordinal()][x].image;
				AffineTransform trans = AffineTransform.getTranslateInstance(0,0);
				trans.scale(0.4 / target.getWidth(), 0.4 / target.getHeight());
				g2.drawImage(target, trans, null);
				
				String number = counts[drawnTypes[x].ordinal()]+"";
				while (number.length() < 3) number = "0"+number;
				g2.translate(0.0, 0.4);
	            for (int i = 0; i < 3; i++) {
	                g2.drawImage(numbers[Integer.decode(number.substring(i, i + 1))], textScaleSmall, null);
	                g2.translate(0.75/4, 0);
	            }
	            g2.translate(-0.75/4*3, 0);

				g2.translate(0.65, -0.4);
				if (x == 2)
						g2.translate(-1.95, 0.7);
			}
			
			
			BufferedImage[] rImage = new BufferedImage[]{ rFusion.image,
																										rVision.image,
																										rDefusion.image,
																										rPickaxe.image,
																										rNuke.image, };

			g2.setTransform(pushed2);
			g2.translate(-0.5, 0.75);
			final double upgradewidth = 0.7;
			final double upgradescale = upgradewidth/0.65;
			g2.translate(0.65*3/2-upgradewidth, 0);
			g2.scale(upgradescale, upgradescale);
			int c = 0;
			for (int u = 0; u < Upgrade.values().length; u++) {
					double research = ds.getResearchProgress(r.getTeam(), u);
					if (research > 0) {
							if (u == rImage.length-1)
							{
								g2.setTransform(pushed2);
								g2.translate(-0.5, 0.75);
								g2.translate(0.65*3/2-upgradewidth, 0);
								g2.scale(upgradescale, upgradescale);
								
								
								BufferedImage target = rImage[u];
								AffineTransform trans = AffineTransform.getTranslateInstance(0,0);
								double finalsize = research>0.5 ? research+0.15 : 0.65;
								trans.scale(finalsize / target.getWidth(), finalsize / target.getHeight());
								g2.translate(0.65-finalsize/2, 1.2);
//								g2.translate(-finalsize+0.65, -finalsize+0.65);
								g2.drawImage(target, trans, null);
								
								g2.scale(finalsize/0.65, finalsize/0.65);
								Rectangle2D.Double rect = new Rectangle2D.Double(0.1, 0.05, 0.5, 0.05f);
								g2.setColor(Color.gray);
								g2.fill(rect);
								double frac = Math.min(research, 1);
								rect.width = frac / 2;
								g2.setColor(Color.green);
								g2.fill(rect);
								
								int roundsleft = (int)((1.00001-research)*Upgrade.NUKE.numRounds);
								if (roundsleft < 55)
								{
									g2.setTransform(pushed2);
									g2.translate(-0.5, 0.75);
									g2.scale(upgradescale, upgradescale);
									g2.translate(0, 1.6);
									
									
									String resource = ""+roundsleft;
									while (resource.length() < 2) resource = "0"+resource;
						            for (int i = 0; i < 2; i++) {
						                g2.drawImage(numbers[Integer.decode(resource.substring(i, i + 1))], textScaleSmall, null);
						                g2.translate(0.75/4, 0);
						            }
								}
							} else
							{
								BufferedImage target = rImage[u];
								AffineTransform trans = AffineTransform.getTranslateInstance(0,0);
								trans.scale(0.65 / target.getWidth(), 0.65 / target.getHeight());
								g2.drawImage(target, trans, null);
								
								Rectangle2D.Double rect = new Rectangle2D.Double(0.1, 0.05, 0.5, 0.05f);
								g2.setColor(Color.gray);
								g2.fill(rect);
								double frac = Math.min(research, 1);
								rect.width = frac / 2;
								g2.setColor(Color.green);
								g2.fill(rect);
								
								g2.translate(0.65, 0);
								if (c == 1 || c == 3)
										g2.translate(-0.65*2, 0.6);
								c++;
							}
							
							
							
//							Rectangle2D.Double rect = new Rectangle2D.Double(0.1, 0.05, 0.5, 0.05f);
//							g2.setColor(Color.gray);
//							g2.fill(rect);
//							double frac = Math.min(research, 1);
//							rect.width = frac / 2;
//							g2.setColor(Color.green);
//							g2.fill(rect);
//
//							g2.translate(0.65, 0);
//							if (c == 2)
//									g2.translate(-0.65*3, 0.6);
//							c++;
					}
			}

			
        }
        g2.setTransform(pushed);
        g2.translate(0, slotSize);
	}
	
}
