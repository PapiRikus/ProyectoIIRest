/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoXml;

/**
 *
 * @author cp
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import EstructurasBasicas.GenericList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.rest.ProyectoServer.GAlgorith3.GladiatorPopulation;
import org.rest.ProyectoServer.models.Gladiador;
import org.rest.ProyectoServer.models.Message;

public class ManagerXml {
//	XStream xstream = new XStream();
	String texto;
	
		//XStream x = new XStream();
		//String path="/home/cp/Documentos/NetbeansProyects/JavaApplicationXml/Data/";
	String path="";
			
        String tipo=".xml";
		
		public ManagerXml(){
			
			
		}
		public String obtenerNodoValor(String strTag,Element ePersona){
		    Node nValor=(Node)ePersona.getElementsByTagName(strTag).item(0).getFirstChild();
		    return nValor.getNodeValue();
		    }
		protected String getString(String tagName, Element element) {
	        NodeList list = element.getElementsByTagName(tagName);
	        if (list != null && list.getLength() > 0) {
	            NodeList subList = list.item(0).getChildNodes();

	            if (subList != null && subList.getLength() > 0) {
	                return subList.item(0).getNodeValue();
	            }
	        }

	        return null;
	    }
		    /*
		    *Este metodo es de https://www.youtube.com/watch?v=eJrlE_03VPQ
		    *fue escogido por su buena implementacion
		    */

                
                public ArrayList<Message>listaasobtenerMessage (String ruta)throws ParserConfigurationException, SAXException, IOException{
			    ArrayList<Message>listaPersonas=new ArrayList<>();
			        
			    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			    
                            DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			        
                            org.w3c.dom.Document doc=docBuilder.parse((path+ruta+tipo));
			    doc.getDocumentElement().normalize();
			        NodeList nodoPersonas=doc.getElementsByTagName("Messsage");
			        for (int i=0;i<nodoPersonas.getLength();i++){
			            Node persona=nodoPersonas.item(i);
			            if (persona.getNodeType()==Node.ELEMENT_NODE){
			                
			                Element unElement=(Element) persona;
			                Message objCheff=new Message();
			                objCheff.setMessage(obtenerNodoValor("message", unElement));
			                objCheff.setAuthor(obtenerNodoValor("author", unElement));
			                
			                listaPersonas.add(objCheff);
			            }
			        }
			        
			        
			        return listaPersonas;
			        }
                public ArrayList<Gladiador>listaasobtenerPopulation (String ruta)throws ParserConfigurationException, SAXException, IOException{
			    ArrayList<Gladiador>listaPersonas=new ArrayList<>();
			        
			    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			    docFactory.setNamespaceAware(false);
                            docFactory.setValidating(false);
                            DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			        
                            org.w3c.dom.Document doc=docBuilder.parse((path+ruta+tipo));
			    doc.getDocumentElement().normalize();
			        NodeList nodoPersonas=doc.getElementsByTagName("Messsage");
			        for (int i=0;i<nodoPersonas.getLength();i++){
			            Node persona=nodoPersonas.item(i);
			            if (persona.getNodeType()==Node.ELEMENT_NODE){
			                
			                Element unElement=(Element) persona;
			                Gladiador gladiador=new Gladiador();
			                gladiador.setNombre(obtenerNodoValor("Nombre", unElement));
			                gladiador.setChromoString(obtenerNodoValor("Chromosome", unElement));
			                
			                listaPersonas.add(gladiador);
			            }
			        }
			        
			        
			        return listaPersonas;
			        }
		public int generacionesenGladiadoresxml(String ruta) throws ParserConfigurationException, SAXException, IOException{
                	    ArrayList<Gladiador>listaPersonas=new ArrayList<>();
			        
			    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			    docFactory.setNamespaceAware(false);
                            docFactory.setValidating(false);
                            DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			        
                            org.w3c.dom.Document doc=docBuilder.parse((path+ruta+tipo));
			    doc.getDocumentElement().normalize();
			        NodeList nodoPersonas=doc.getElementsByTagName("PopulationGeneracion");
                                return nodoPersonas.getLength();
                }
                public ArrayList<Gladiador>listaasobtenerGladiatorPopulation (String ruta)throws ParserConfigurationException, SAXException, IOException{
			    ArrayList<Gladiador>listaPersonas=new ArrayList<>();
			        
			    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			    docFactory.setNamespaceAware(false);
                            docFactory.setValidating(false);
                            
                            DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			        
                            org.w3c.dom.Document doc=docBuilder.parse((path+ruta+tipo));
			    doc.getDocumentElement().normalize();
			        NodeList nodoPersonas=doc.getElementsByTagName("PopulationGeneracion");
                                System.out.println("NodoGeneracion"+nodoPersonas.getLength()+"");
			        for (int i=nodoPersonas.getLength();i<nodoPersonas.getLength();i++){
                                    
                                     NodeList nodoPersonass=doc.getElementsByTagName("Gladiador");
                                     System.out.println("Poblacion:"+nodoPersonass.getLength());
			        
                                    for (int j=0;j<nodoPersonass.getLength();j++){
                                    
                                    
			            Node persona=nodoPersonass.item(j);
			            if (persona.getNodeType()==Node.ELEMENT_NODE){
			                
			                Element unElement=(Element) persona;
			                Gladiador gladiador=new Gladiador();
			                gladiador.setNombre(obtenerNodoValor("Nombre", unElement));
			                gladiador.setChromoString(obtenerNodoValor("Chromosome", unElement));
			                
			                listaPersonas.add(gladiador);
			            }
                                    }
                                }
			        
			        
			        return listaPersonas;
			        }
		
		///Esta es la que guarda con generaciones 
                public void agregarGanador(String ruta,Message msj) throws SAXException, IOException, ParserConfigurationException, TransformerException{
                                      
		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
                        docFactory.setNamespaceAware(false);
                            docFactory.setValidating(false);
                            
		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		        ruta+=".xml";
		        File file=new File(path+ruta);
		         if(!file.exists()){
		             file.createNewFile();
		            }
		         
		        Document doc=docBuilder.parse(new File(path+ruta));
		        doc.getDocumentElement().normalize();
		        Node nodoRaiz=doc.getDocumentElement();
		        Element nuevaPoblacion=doc.createElement("ResultadodelaBatalla");                        
                        
                        Element nuevaGanador=doc.createElement("Ganador");
                        System.out.println("Nombre del ganador:"+msj.getAuthor());
                        nuevaGanador.setTextContent(msj.getAuthor());
                       // Element nuevaPerdedor=doc.createElement("Perdedor");
                       // nuevaPerdedor.setTextContent(msj2.getAuthor());
                        nuevaPoblacion.appendChild(nuevaGanador);
                        //nuevaPoblacion.appendChild(nuevaPerdedor);
                        
                        nodoRaiz.appendChild(nuevaPoblacion);
                         TransformerFactory transFactory=TransformerFactory.newInstance();

		        Transformer transformer=transFactory.newTransformer();
		        DOMSource source=new DOMSource(doc);
		        StreamResult result=new StreamResult(new File(path+ruta));
		        transformer.transform(source, result);
                }
                public void agregarGladiatorPoblacion(GladiatorPopulation population,String ruta) throws SAXException, ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException{
                            
		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
                        docFactory.setNamespaceAware(false);
                            docFactory.setValidating(false);
                            
		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		        ruta+=".xml";
		        File file=new File(path+ruta);
		         if(!file.exists()){
		             file.createNewFile();
		            }
		         
		        Document doc=docBuilder.parse(new File(path+ruta));
		        doc.getDocumentElement().normalize();
		        Node nodoRaiz=doc.getDocumentElement();
		        Element nuevaPoblacion=doc.createElement("PopulationGeneracion");
                       // nuevaPoblacion.setTextContent(""+population.getGeneracion());
                        Element nuevaGeneracion=doc.createElement("Generacion");
                        nuevaGeneracion.setTextContent(""+population.getGeneracion());
                        
                        
                        Element nuevaMutacion=doc.createElement("Mutaciones");
                        nuevaMutacion.setTextContent(""+population.getMutaciones());
                        Element nuevaInversion=doc.createElement("Inversiones");
                        nuevaInversion.setTextContent(""+population.getIversiones());
                        
                        
                        nuevaPoblacion.appendChild(nuevaGeneracion);
                        nuevaPoblacion.appendChild(nuevaMutacion);
                        nuevaPoblacion.appendChild(nuevaInversion);
                        
                        for (int i =0;i<population.getGladiator().length;i++){
		        Element nuevaoGladiador=doc.createElement("Gladiador");
                        
		        Element nuevaoNombre=doc.createElement("Nombre"); 
                        nuevaoNombre.setTextContent(population.getGladiator()[i].getNombre());
		        
		        Element nuevaoFitness=doc.createElement("Fitness"); 
                        nuevaoFitness.setTextContent(population.getGladiator()[i].getChromosome().getFitness()+"");
		       
                        Element nuevoChrmosoma=doc.createElement("Chromosome");
		        nuevoChrmosoma.setTextContent(Arrays.toString(population.getGladiator()[i]
                                .getChromosome().getGenes()));
		        
		        nuevaoGladiador.appendChild(nuevaoNombre);
		        nuevaoGladiador.appendChild(nuevaoFitness);
                        nuevaoGladiador.appendChild(nuevoChrmosoma);
		        nuevaPoblacion.appendChild(nuevaoGladiador);
		        
                        }
                                                
                        nodoRaiz.appendChild(nuevaPoblacion);  
                      
                        /////////7
                        
                        ////
		        TransformerFactory transFactory=TransformerFactory.newInstance();

		        Transformer transformer=transFactory.newTransformer();
		        DOMSource source=new DOMSource(doc);
		        StreamResult result=new StreamResult(new File(path+ruta));
		        transformer.transform(source, result);
                }
                
                public void agregarGladiatorPoblacion(GladiatorPopulation population,String ruta,int numPoblacion) throws SAXException, ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException{
                            
		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		     
		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		        ruta+=".xml";
		        File file=new File(path+ruta);
		         if(!file.exists()){
		             file.createNewFile();
		            }
		         
		        Document doc=docBuilder.parse(new File(path+ruta));
		        doc.getDocumentElement().normalize();
		        Node nodoRaiz=doc.getDocumentElement();
                         Element nuevaPoblacionNume=doc.createElement("Population"+numPoblacion);
                       
                        Element nuevaGeneracion=doc.createElement("Generacion"+population.getGeneracion());
                        for (int i =0;i<population.getGladiator().length;i++){
		        Element nuevaoGladiador=doc.createElement("Gladiador");
                        
		        Element nuevaoNombre=doc.createElement("Nombre"); 
                        nuevaoNombre.setTextContent(population.getGladiator()[i].getNombre());
		        
		        Element nuevoChrmosoma=doc.createElement("Chromosome");
		        nuevoChrmosoma.setTextContent(Arrays.toString(population.getGladiator()[i]
                                .getChromosome().getGenes()));
		        
		        nuevaoGladiador.appendChild(nuevaoNombre);
		        nuevaoGladiador.appendChild(nuevoChrmosoma);
		        nuevaGeneracion.appendChild(nuevaoGladiador);
		        
                        }
                                                
                      
                        nodoRaiz.appendChild(nuevaGeneracion);  
                      
                        /////////7
                        
                        ////
		        TransformerFactory transFactory=TransformerFactory.newInstance();

		        Transformer transformer=transFactory.newTransformer();
		        DOMSource source=new DOMSource(doc);
		        StreamResult result=new StreamResult(new File(path+ruta));
		        transformer.transform(source, result);
                }
//                public void agregarPoblacion(Population population,String ruta) throws SAXException, ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException{
//                            
//		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
//		     
//		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
//		        ruta+=".xml";
//		        File file=new File(path+ruta);
//		         if(!file.exists()){
//		             file.createNewFile();
//		            }
//		         
//		        Document doc=docBuilder.parse(new File(path+ruta));
//		        doc.getDocumentElement().normalize();
//		        Node nodoRaiz=doc.getDocumentElement();
//		        Element nuevaPoblacion=doc.createElement("PopulationGeneracion"+population.getGeneracion());
//                        for (int i =0;i<population.getChromosomes().length;i++){
//		        Element nuevaoGladiador=doc.createElement("Gladiador");
//                        
//		        Element nuevaoNombre=doc.createElement("Nombre"); 
//                        nuevaoNombre.setTextContent("glad#"+i);
//		        
//		        Element nuevoChrmosoma=doc.createElement("Chromosome");
//		        nuevoChrmosoma.setTextContent(Arrays.toString(population.getChromosomes()[i].getGenes()));
//		        
//		        nuevaoGladiador.appendChild(nuevaoNombre);
//		        nuevaoGladiador.appendChild(nuevoChrmosoma);
//		        nuevaPoblacion.appendChild(nuevaoGladiador);
//		        
//                        }
//                                                
//                      
//                        nodoRaiz.appendChild(nuevaPoblacion);  
//                      
//                        /////////7
//                        
//                        ////
//		        TransformerFactory transFactory=TransformerFactory.newInstance();
//
//		        Transformer transformer=transFactory.newTransformer();
//		        DOMSource source=new DOMSource(doc);
//		        StreamResult result=new StreamResult(new File(path+ruta));
//		        transformer.transform(source, result);
//                }  
                  
                public void agregarMessage(Message persona,String ruta) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
		        
		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		     
		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		        ruta+=".xml";
		        File file=new File(path+ruta);
		         if(!file.exists()){
		             file.createNewFile();
		            }
		         
		        Document doc=docBuilder.parse(new File(path+ruta));
		        doc.getDocumentElement().normalize();
		        Node nodoRaiz=doc.getDocumentElement();
		        Element nuevaPersona=doc.createElement("Messsage");
		        
		        Element nuevaoNombre=doc.createElement("message");
		        nuevaoNombre.setTextContent(persona.getMessage());
		        
		        Element nuevaoApellido=doc.createElement("author");
		        nuevaoApellido.setTextContent(persona.getAuthor());
		        
		        nuevaPersona.appendChild(nuevaoNombre);
		        nuevaPersona.appendChild(nuevaoApellido);
		        
		        
		        nodoRaiz.appendChild(nuevaPersona);
		        
		        TransformerFactory transFactory=TransformerFactory.newInstance();

		        Transformer transformer=transFactory.newTransformer();
		        DOMSource source=new DOMSource(doc);
		        StreamResult result=new StreamResult(new File(path+ruta));
		        transformer.transform(source, result);
		    }
                public void agregarMessage(Message persona,String ruta,int a) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
		        
		        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		     
		        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		        ruta+=".xml";
		        File file=new File(path+ruta);
		         if(!file.exists()){
		             file.createNewFile();
		            }
		         
		        Document doc=docBuilder.parse(new File(path+ruta));
		        doc.getDocumentElement().normalize();
		        Node nodoRaiz=doc.getDocumentElement();
                        Element nuevoGrupoDeMensajes=doc.createElement("Message"+1);
                        for (int i=0;i<3;i++){
                        
		        Element nuevaPersona=doc.createElement("Messsage");
		        
		        Element nuevaoNombre=doc.createElement("message");
		        nuevaoNombre.setTextContent(persona.getMessage());
		        
		        Element nuevaoApellido=doc.createElement("author");
		        nuevaoApellido.setTextContent(persona.getAuthor());
		        
		        nuevaPersona.appendChild(nuevaoNombre);
		        nuevaPersona.appendChild(nuevaoApellido);
                        nuevoGrupoDeMensajes.appendChild(nuevaPersona);
		       
                        }
		        
                        nodoRaiz.appendChild(nuevoGrupoDeMensajes);
		        
		        TransformerFactory transFactory=TransformerFactory.newInstance();

		        Transformer transformer=transFactory.newTransformer();
		        DOMSource source=new DOMSource(doc);
		        StreamResult result=new StreamResult(new File(path+ruta));
		        transformer.transform(source, result);
		    }
		    
//		    public ArrayList<Platillo>listaobtenerPlatillo (String ruta)throws ParserConfigurationException, SAXException, IOException{
//			    ArrayList<Platillo>listaPersonas=new ArrayList<>();
//			        
//			    DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
//			    DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
//			        
//			    org.w3c.dom.Document doc=docBuilder.parse(new File(path+ruta+".xml"));
//			        doc.getDocumentElement().normalize();
//			        NodeList nodoPersonas=doc.getElementsByTagName(this.SPlatillo);
//			        for (int i=0;i<nodoPersonas.getLength();i++){
//			            Node persona=nodoPersonas.item(i);
//			            if (persona.getNodeType()==Node.ELEMENT_NODE){
//			                
//			                Element unElement=(Element) persona;
//			                Platillo objCheff=new Platillo();
////			                		obtenerNodoValor("nombre", unElement),
////			                		obtenerNodoValor("ingredientes", unElement), 
////			                		obtenerNodoValor("informacionNutricional", unElement),
////			                		
////			                		Integer.parseInt(obtenerNodoValor("precio", unElement)), 
////			                		
////			                				obtenerNodoValor("tiempo_de_preparacion", unElement),
////			                		obtenerNodoValor("receta", unElement);
////			                
//			                
//			                listaPersonas.add(objCheff);
//			            }
//			        }
//			        
//			        
//			        return listaPersonas;
//			        }
//		public String AgregarEtiquetas(String EtiquetaI,String EtiquetaF,String txt){
//			EtiquetaI = txt;
//			txt+=EtiquetaF;
//			return txt;
//			
//			
//		}
//		public String QuitarEtiquetaPrincipal(String EtiquetaF,String contenido){
//			String aux=contenido.substring(7);
//			String aux2=aux.split(EtiquetaF)[0];
//			return aux2;
//		}
//		public void AgregarCheff(Cheff c,String ruta){
//			String rC=path+ruta+tipo;
//			String contenido=this.LeerFichero(rC);
//			String aux=getXstream().toXML(c);
//			String guardar=this.QuitarEtiquetaPrincipal("</ListaCheff>",aux);
//			guardar+=aux;
//			
//			
//		}
//		/*Este codigo no es de mi pertenencia fue sacado de esta pagina por su buena implementacion
//		 * http://todoelmed.blogspot.com/2012/05/manejo-de-archivos-o-ficheros-en.html
//		 */
//		
//		public void GuardarFichero(String ruta,String SCadena){
//			  try {
//				  
//				  File Ffichero=new File(path+ruta+".xml");
//			        //Si no Existe el fichero lo crea
//			         if(!Ffichero.exists()){
//			            Ffichero.createNewFile();
//			         }
//		         /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8. 
//	           *Además  en el pedazo de sentencia "FileOutputStream(Ffichero,true)",
//	           *true es por si existe el fichero seguir añadiendo texto y no borrar lo que tenia*/
//			         BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero,true), "utf-8"));
//		          /*Escribe en el fichero la cadena que recibe la función. 
//		           *el string "\r\n" significa salto de linea*/
//		          Fescribe.write(SCadena + "\r\n");
//		          //Cierra el flujo de escritura
//		          Fescribe.close();
//		       } catch (Exception ex) {
//		          //Captura un posible error le imprime en pantalla 
//		          System.out.println(ex.getMessage());
//		        } 
//			}
//		
//		public String LeerFichero(String ruta){
//			File Ffichero=new File(path+ruta+".xml");   
//			String aux="";
//			       
//			try {
//				   /*Si existe el fichero*/
//			       if(Ffichero.exists()){
//			           /*Abre un flujo de lectura a el fichero*/
//			           BufferedReader Flee= new BufferedReader(new FileReader(Ffichero));
//			           String Slinea;
//			           System.out.println("**********Leyendo Fichero***********");
//			           /*Lee el fichero linea a linea hasta llegar a la ultima*/
//			           
//			           while((Slinea=Flee.readLine())!=null) {
//			           /*Imprime la linea leida*/    
//			           System.out.println(Slinea);  
//			           aux+=Slinea;
//			           }
//			           System.out.println("*********Fin Leer Fichero**********");
//			           /*Cierra el flujo*/
//			           Flee.close();
//			         }else{
//			           System.out.println("Fichero No Existe");
//			       
//			         }
//			   } catch (Exception ex) {
//			       /*Captura un posible error y le imprime en pantalla*/ 
//			        System.out.println(ex.getMessage());
//			   }
//			return aux;
//			 }
		 
	//	
//		public void guardarxml (Object ob,String ruta) throws IOException{
//	    // Grabar
//			
//			this.texto=x.toXML(new Cheff("david", "43"));
//			System.out.println(texto+"Esta por aca ");
//			
//			 x.toXML(ob, new FileOutputStream(ruta));
//			 guardar("esto.txt", texto);
//		}
//		public  Object leerXml(String ruta) throws FileNotFoundException{
//	    // Recuperar
//	     return  x.fromXML(new FileInputStream(ruta));
	//
	//	
//		}
}