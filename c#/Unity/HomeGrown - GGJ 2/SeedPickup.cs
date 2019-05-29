using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SeedPickup : MonoBehaviour
{
	public int type;
	public AudioClip mySound;
    // Start is called before the first frame update
    void Start()
    {
    }

    // Update is called once per frame
    void Update()
    {
        
    }

	private void OnTriggerEnter2D(Collider2D collision)
	{
		if(collision.gameObject.tag == "Player")
		{
			MCP.mcp.soundBox.clip = mySound;
			MCP.mcp.soundBox.Play();
			if (type == 0)
				MCP.mcp.woodSeeds += 1;
			if (type == 1)
				MCP.mcp.stoneSeeds += 1;
			if (type == 2)
				MCP.mcp.metalSeeds += 1;
			if (type == 3)
				MCP.mcp.waterSeeds += 1;
			Destroy(gameObject);
		}
	}
}
