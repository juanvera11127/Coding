using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class BuildMenuButton : MonoBehaviour
{
	public int woodCost, stoneCost, metalCost, waterCost;
	public Text woodText, stoneText, metalText, waterText;
	public int songType;
	public string objName;
	public AudioClip success;
	public AudioClip fail;
	void Start()
	{
		woodText.text = " x" + woodCost;
		stoneText.text = " x" + stoneCost;
		metalText.text = " x" + metalCost;
		waterText.text = " x" + waterCost;
	}
	
	public void Build()
	{
		if(MCP.mcp.woodSeeds >= woodCost && MCP.mcp.stoneSeeds >= stoneCost && MCP.mcp.metalSeeds >= metalCost && MCP.mcp.waterSeeds >= waterCost)
		{
			MCP.mcp.woodSeeds -= woodCost;
			MCP.mcp.stoneSeeds -= stoneCost;
			MCP.mcp.metalSeeds -= metalCost;
			MCP.mcp.waterSeeds -= waterCost;
			MCP.DDRC.progressToAdd = woodCost + stoneCost + metalCost + waterCost;
			MCP.mcp.soundBox.clip = success;
			MCP.mcp.soundBox.Play();
			MCP.DDRC.gameObject.SetActive(true);
			MCP.DDRC.nameToBuild = objName;
			MCP.DDRC.StartDDR(songType);
			transform.parent.parent.parent.gameObject.SetActive(false);
			MCP.mcp.muDig.SetActive(false);
		}
		else
		{
			MCP.mcp.soundBox.clip = fail;
			MCP.mcp.soundBox.Play();
			MCP.player.SetActive(true);
			MCP.mcp.muDig.SetActive(false);
			MCP.player.GetComponent<PlayerControls>().canControl = true;

			transform.parent.parent.parent.gameObject.SetActive(false);
		}
	}
}
