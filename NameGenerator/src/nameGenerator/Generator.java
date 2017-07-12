package nameGenerator;

import java.util.Random;

public class Generator {

	private Options op;
	private String tempName;
	private String[] tab = {"e","i","a","o","ho","le","od","he","ec","in","re","ha","ir","en","on","y"}; 
	private String[] tab2 = {"azer","tion","ont","beng","tar","ent","ion","mo","om","dem","rion","mur","d","tron","rei","cro","azur","ark","kor","ikt","tom","ryk","ik","or"};
	private String[] tab3 = {"hif","rdm","alf","dri","enk","zard","nome","ursona","berty","onel","tria","ock","phis","sytr","noneu","eban","botar","disl","darbus","ron","alla","achscops","ilda",
			"dertw","rub","banar","rumo","etle","eneh"};
	
	public Generator(Options op) {
		this.op = op;
	}

	public String randomName(){
		
		if(op.getTabSize()!=0){
			
		Random rnd = new Random();
		int id = rnd.nextInt(op.getTabSize());
		String name = op.getTabValue(id);
		if(name.equals(tempName) && op.getTabSize()!=1){
			name = randomName();
		}
		tempName = name;
		return name;
		}
		return null;
	}
	
	public String gameNameGenerator(String name) {
		String gameName = null;

		char firstLetter = (char) RandomNumber(90,65);
		String lastLetter = tab2[RandomNumber(tab2.length-1, 0)];
		String secondLetter = tab[RandomNumber(tab.length-1,0 )];
		String fourth = tab3[RandomNumber(tab3.length-1, 0)];
		
		if(name.length()>3) {
			String part1 = name.substring(1, name.length()/2);
			String part2 = name.substring(name.length()/2,name.length());
			
			gameName = firstLetter +part1 + secondLetter + part2 + lastLetter;
		}else if(name.length()>0) { 
		gameName = firstLetter + secondLetter + name + fourth +  lastLetter;
		}else {
		gameName = firstLetter + secondLetter + name + fourth +  lastLetter;
		}
		return gameName;
	}
	
	private int RandomNumber(int a, int b) {
		Random rnd = new Random();
		return rnd.nextInt(a + 1 - b)+b;
		
	}
	
}
