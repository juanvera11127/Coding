using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TutorialController : MonoBehaviour
{
	public Animator anim;
	public Animator fairyAnim;
	public GameObject danceMu;
	public GameObject DDRGUI;
	public GameObject fire;
	public GameObject barrier;
	public ConstrainedFollow camControl;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
	public void Retry()
	{
		MCP.player.SetActive(false);
		danceMu.SetActive(true);
		DDRGUI.SetActive(true);
		DDRGUI.GetComponent<DDRController>().tut = true;
		DDRGUI.GetComponent<DDRController>().StartDDR(5);
	}
	public void StartTutorial()
	{
		fairyAnim.SetTrigger("TutorialStart");
		MCP.player.SetActive(false);
		danceMu.SetActive(true);
		DDRGUI.SetActive(true);
		DDRGUI.GetComponent<DDRController>().tut = true;
		DDRGUI.GetComponent<DDRController>().StartDDR(5);
	}
	public void GrowHouse1()
	{
		barrier.SetActive(false);
		fire.SetActive(false);
		MCP.player.transform.position = new Vector3(2,2,0);
		anim.SetTrigger("GrowHouse1");
	}
}
