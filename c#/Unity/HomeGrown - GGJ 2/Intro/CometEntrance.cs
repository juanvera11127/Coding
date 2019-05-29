using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CometEntrance : MonoBehaviour
{
	public GameObject comet;
	public GameObject cometLanded;
	public TutorialController tutControl;
	public CometTouch tutTouch;
	public bool skipIntro;

    void Start()
    {
		if(!skipIntro)
			StartCoroutine(Intro());
		else
		{
			SkipIntro();
		}
    }

	void SkipIntro()
	{
		MCP.player.GetComponent<PlayerControls>().StartWake(0);
		tutTouch.darkMask.SetActive(false);
		tutControl.barrier.SetActive(false);
		tutControl.GrowHouse1();
	}

	IEnumerator Intro()
	{
		yield return new WaitForSeconds(10);
		comet.SetActive(true);
		yield return new WaitForSeconds(4);
		cometLanded.SetActive(true);
		MCP.cameraShake.Shake(3, 0.2f);
		MCP.player.GetComponent<PlayerControls>().StartWake(2);
		yield return new WaitForSeconds(3);
		comet.SetActive(false);
	}
}
