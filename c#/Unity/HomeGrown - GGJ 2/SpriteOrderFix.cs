using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpriteOrderFix : MonoBehaviour
{
	private SpriteRenderer mySprite;
    // Start is called before the first frame update
    void Start()
    {
		mySprite = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
		if (transform.position.y > MCP.player.transform.position.y)
			mySprite.sortingOrder = 4;
		else if (transform.position.y < MCP.player.transform.position.y)
			mySprite.sortingOrder = 6;
	}
}
